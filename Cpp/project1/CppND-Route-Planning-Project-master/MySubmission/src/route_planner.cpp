#include "route_planner.h"
#include <algorithm>

RoutePlanner::RoutePlanner(RouteModel &model, float start_x, float start_y, float end_x, float end_y): m_Model(model) {
    // Convert inputs to percentage:
    start_x *= 0.01;
    start_y *= 0.01;
    end_x *= 0.01;
    end_y *= 0.01;
std:: cout << "Hi From RoutePlanner \n";
    // TODO 2: Use the m_Model.FindClosestNode method to find the closest nodes to the starting and ending coordinates.
    this->start_node = &m_Model.FindClosestNode(start_x,start_y);
    this->start_node->g_value = 0;
    this->end_node = &m_Model.FindClosestNode(end_x,end_y);
    // Store the nodes you find in the RoutePlanner's start_node and end_node attributes.
    //[KO] Do I need to this or Can I use this->start_node = m_Model...etc;
}


// TODO 3: Implement the CalculateHValue method.
// Tips:
// - You can use the distance to the end_node for the h value.
// - Node objects have a distance method to determine the distance to another node.

float RoutePlanner::CalculateHValue(RouteModel::Node const *node) {
//std::cout << "Hi From CalculateHValue \n";
    if (node != nullptr)
    {
        float h ;
        auto x1 = node->x;
        auto y1 = node->y;
        auto x2 = this->end_node->x;
        auto y2 = this->end_node->y;
        //std::cout << "The x2,y1,x2,y2: " << x1 << y1 << x2 << y2 << "\n";
        h = sqrt(pow(x2 - x1, 2) + pow(y2 - y1, 2));
        return h;
    }
    //Invalid input (Null ptr)
    return -1;
}


// TODO 4: Complete the AddNeighbors method to expand the current node by adding all unvisited neighbors to the open list.
// Tips:
// - Use the FindNeighbors() method of the current_node to populate current_node.neighbors vector with all the neighbors.
// - For each node in current_node.neighbors, set the parent, the h_value, the g_value. 
// - Use CalculateHValue below to implement the h-Value calculation.
// - For each node in current_node.neighbors, add the neighbor to open_list and set the node's visited attribute to true.

void RoutePlanner::AddNeighbors(RouteModel::Node *current_node) {
    //std:: cout << "Hi From AddNeighbors \n";
    current_node->FindNeighbors();
   // std::cout << "Done with FindNeighbors with number of neighbors: "<< current_node->neighbors.size()<< "\n";
    for(auto neighbor : current_node->neighbors)
    {
        //KO need to double check if the GetDistance is enough
        auto g = current_node->g_value + neighbor->distance(*(current_node));;
        auto h = this->CalculateHValue(neighbor);
        auto f = g+ h;
    
       if (!neighbor->visited || f < (neighbor->g_value + neighbor->h_value)) {
            neighbor->g_value = g;
            neighbor->h_value = h;
            neighbor->parent = current_node;
            open_list.push_back(neighbor);
            neighbor->visited = true;
        }

    }
   // std::cout << "Done with AddNeighbors \n";
}


// TODO 5: Complete the NextNode method to sort the open list and return the next node.
// Tips:
// - Sort the open_list according to the sum of the h value and g value.
// - Create a pointer to the node in the list with the lowest sum.
// - Remove that node from the open_list.
// - Return the pointer.

RouteModel::Node *RoutePlanner::NextNode() {
   // std::cout << "NextNode \n";
    if (open_list.size())
    {
        auto lowest_sum = open_list[0]->g_value + open_list[0]->h_value;
        auto index = 0;
        for (auto i = 0 ; i< open_list.size(); i++)
        {
            auto f = open_list[i]->g_value + open_list[i]->h_value;
            //std::cout << "f value = " << f << "\n";
            if (f < lowest_sum)
            {
                index = i;
                lowest_sum = f;
            }
        }
        //After getting the lowest sum remove it and return it
        RouteModel::Node* lowest_h_node = open_list[index];
      //  std::cout << "min f value = " << lowest_h_node->h_value + lowest_h_node->g_value << "\n";
        open_list.erase(open_list.begin() +index);
        return lowest_h_node;
    }
    
}


// TODO 6: Complete the ConstructFinalPath method to return the final path found from your A* search.
// Tips:
// - This method should take the current (final) node as an argument and iteratively follow the 
//   chain of parents of nodes until the starting node is found.
// - For each node in the chain, add the distance from the node to its parent to the distance variable.
// - The returned vector should be in the correct order: the start node should be the first element
//   of the vector, the end node should be the last element.

std::vector<RouteModel::Node> RoutePlanner::ConstructFinalPath(RouteModel::Node *current_node) {
   // std::cout << "Hi From ConstructFinalPath \n";
    // Create path_found vector
    distance = 0.0f;
    std::vector<RouteModel::Node> path_found;
    // TODO: Implement your solution here.
    path_found.push_back(*current_node);
    auto counter = 1;
    while (current_node->parent != this->start_node)
    {
        counter ++;
        distance += current_node->distance(*(current_node->parent));
        path_found.push_back(*current_node->parent);
        current_node = current_node->parent;
    }
   // std::cout << "Counter " << ++counter << "\n";
    path_found.push_back(*(this->start_node));
    distance += current_node->distance(*(this->start_node));
    // reverse to get the right Order
    std::reverse(path_found.begin(),path_found.end());
    distance *= m_Model.MetricScale(); // Multiply the distance by the scale of the map to get meters.
    return path_found;
}


// TODO 7: Write the A* Search algorithm here.
// Tips:
// - Use the AddNeighbors method to add all of the neighbors of the current node to the open_list.
// - Use the NextNode() method to sort the open_list and return the next node.
// - When the search has reached the end_node, use the ConstructFinalPath method to return the final path that was found.
// - Store the final path in the m_Model.path attribute before the method exits. This path will then be displayed on the map tile.

void RoutePlanner::AStarSearch() {
    RouteModel::Node *current_node = nullptr;
    // TODO: Implement your solution here.
    current_node = this->start_node;
    start_node->visited = true;
    this->AddNeighbors(current_node);
    while(open_list.size()!= 0)
    {
        current_node = this->NextNode();
        this->AddNeighbors(current_node);
        if (current_node == this->end_node)
        {
            std:: cout << "It is Found! \n";
            m_Model.path = this->ConstructFinalPath(current_node);
            break;
        }
    }
    if (open_list.size() == 0)
    {
          std:: cout << "It is not Found!!! \n";
    }
}