import heapq
from collections import defaultdict
from typing import Optional, Dict, Tuple


class HuffmanNode:
    """
    A class to represent a node in the Huffman Tree.
    """

    def __init__(self, char: Optional[str], freq: int) -> None:
        self.char = char
        self.freq = freq
        self.left = None
        self.right = None

    def __lt__(self, other: 'HuffmanNode') -> bool:
        return self.freq < other.freq


def calculate_frequencies(data: str) -> Dict[str, int]:
    """
    Calculate the frequency of each character in the given data.
    """
    frequencies = defaultdict(int)
    for char in data:
        frequencies[char] += 1
    print (frequencies)
    return dict(frequencies)


def build_huffman_tree(frequency: Dict[str, int]) -> HuffmanNode:
    """
    Build the Huffman Tree based on the character frequencies.
    """
    # Create a priority queue (min-heap)
    heap = [HuffmanNode(char, freq) for char, freq in frequency.items()]
    heapq.heapify(heap)
    # Loop all over the items till the Len = 1 (the root of the tree)
    if len(heap) == 1:
        return heap[0]
    while len(heap) > 1:
        # Pop two nodes with the lowest frequencies
        node1 = heapq.heappop(heap)
        node2 = heapq.heappop(heap)

        # Create a new internal node with these two as children
        merged_node = HuffmanNode(None, node1.freq + node2.freq)
        merged_node.left = node1
        merged_node.right = node2

        # Add the new node back to the heap
        heapq.heappush(heap, merged_node)

    # The root node is the only remaining node in the heap
    return heap[0]


def generate_huffman_codes(node: Optional[HuffmanNode], code: str, huffman_codes: Dict[str, str]) -> None:
    """
    Generate Huffman codes for each character by traversing the Huffman Tree.
    """
    if node is None:
        return

    # If it's a leaf node, store the character and its code
    if node.char is not None:
        huffman_codes[node.char] = code
        return

    # Traverse left (add "0" to the code) and right (add "1" to the code)
    generate_huffman_codes(node.left, code + "0", huffman_codes)
    generate_huffman_codes(node.right, code + "1", huffman_codes)


def huffman_encoding(data: str) -> Tuple[str, Optional[HuffmanNode]]:
    """
    Encode the given data using Huffman coding.
    """
    if not data:
        return "", None

    # Step 1: Calculate frequencies
    # print(data)
    frequencies = calculate_frequencies(data)
    # print(frequencies)
    # Special case: Single unique character
    if len(frequencies) == 1:
        char = next(iter(frequencies))  # Get the single character
        return '', HuffmanNode(char, frequencies[char])
    # Step 2: Build Huffman Tree
    root = build_huffman_tree(frequencies)
    # print(root.freq)
    # Step 3: Generate Huffman Codes
    huffman_codes = {}
    generate_huffman_codes(root, "", huffman_codes)
    # print(huffman_codes)
    # Step 4: Encode the data
    encoded_data = "".join(huffman_codes[char] for char in data)
    
    return encoded_data, root


def huffman_decoding(encoded_data: str, tree: Optional[HuffmanNode]) -> str:
    """
    Decode the given encoded data using the Huffman Tree.
    """
    # print(tree)
    # print(encoded_data)
    if not encoded_data and tree and tree.char is not None:
        # Special case: Single unique character
        return tree.char * tree.freq

    if not encoded_data or tree is None:
        return ""

    decoded_data = []
    current_node = tree

    for bit in encoded_data:
        # Traverse the tree: 0 -> left, 1 -> right
        current_node = current_node.left if bit == "0" else current_node.right

        # If it's a leaf node, append the character and reset the traversal
        if current_node.char is not None:
            decoded_data.append(current_node.char)
            current_node = tree

    return "".join(decoded_data)


# Main Function
if __name__ == "__main__":
    # Test Case 1: Standard sentence
    print("\nTest Case 0: Given sentence")
    sentence = "AAAAAAABBBCCCCCCCDDEEEEEE"
    encoded_data, tree = huffman_encoding(sentence)
    print("Encoded:", encoded_data)
    decoded_data = huffman_decoding(encoded_data, tree)
    print("Decoded:", decoded_data)
    assert sentence == decoded_data
    # Test Case 1: Standard sentence
    print("\nTest Case 1: Standard sentence")
    sentence = "The bird is the word"
    encoded_data, tree = huffman_encoding(sentence)
    print("Encoded:", encoded_data)
    decoded_data = huffman_decoding(encoded_data, tree)
    print("Decoded:", decoded_data)
    assert sentence == decoded_data

    # Test Case 2: Single character
    print("\nTest Case 2: Single character")
    sentence = "aaaaaa"
    encoded_data, tree = huffman_encoding(sentence)
    print("Encoded:", encoded_data)
    decoded_data = huffman_decoding(encoded_data, tree)
    print("Decoded:", decoded_data)
    assert sentence == decoded_data

    # Test Case 3: Empty string
    print("\nTest Case 3: Empty string")
    sentence = ""
    encoded_data, tree = huffman_encoding(sentence)
    print("Encoded:", encoded_data)
    decoded_data = huffman_decoding(encoded_data, tree)
    print("Decoded:", decoded_data)
    assert sentence == decoded_data

