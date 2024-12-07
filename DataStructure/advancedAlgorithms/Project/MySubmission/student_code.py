import heapq
from math import sqrt
from typing import Optional


class Node:
    def __init__(self, id, h, g):
        self.id = id
        self.h = h
        self.g = g
        self.f = 0
        self.isVisited = False

    def setH(self, h) -> float:
        self.h = h

    def setG(self, g) -> float:
        self.g = g

    def getF(self) -> float:
        self.f = self.h + self.g
        return self.f

    def getG(self) -> float:
        return self.g

    def getId(self):
        return self.id


def heuristic(a: tuple[float, float], b: tuple[float, float]) -> float:
    return sqrt((b[0] - a[0]) ** 2 + (b[1] - a[1]) ** 2)


def reconstruct_path(came_from: dict[int, int], current: int) -> list[int]:
    path = []
    while current in came_from:
        path.append(current)
        current = came_from[current]
    path.append(current)
    return path[::-1]


def shortest_path(M: 'Map', start: int, goal: int) -> Optional[list[int]]:
    open_set = []
    heapq.heappush(open_set, (0, start))
    came_from = {}
    g_score = {node: float('inf') for node in M.intersections}
    g_score[start] = 0
    visited = set()

    # print(f"Starting A* algorithm from node {start} to node {goal}.")

    while open_set:
        _, current = heapq.heappop(open_set)

        # print(f"Processing node {current}.")

        if current == goal:
            # print("Goal node reached. Reconstructing path.")
            return reconstruct_path(came_from, current)

        if current in visited:
            # print(f"Node {current} already visited. Skipping.")
            continue

        visited.add(current)
        # print(f"Marking node {current} as visited.")

        for neighbor in M.roads[current]:
            if neighbor in visited:
                # print(f"Neighbor {neighbor} already visited. Skipping.")
                continue

            tentative_g_score = g_score[current] + heuristic(M.intersections[current], M.intersections[neighbor])

            if tentative_g_score < g_score[neighbor]:
                # print(f"Found a better path to node {neighbor}. Updating scores from {g_score[neighbor]} to {tentative_g_score}.")
                came_from[neighbor] = current
                g_score[neighbor] = tentative_g_score
                f_score = tentative_g_score + heuristic(M.intersections[neighbor], M.intersections[goal])
                heapq.heappush(open_set, (f_score, neighbor))
                # print(f"Pushed node {neighbor} with f={f_score} into the priority queue.")

    print("No path found.")
    return None
