from ipywidgets import widgets
from IPython.display import display
from ipywidgets import interact
# Define TrieNode
class TrieNode:
    def __init__(self):
        self.children = {}
        self.is_end_of_word = False

    def insert(self, char):
        if char not in self.children:
            self.children[char] = TrieNode()

    def suffixes(self, suffix=''):
        suffixes = []
        if self.is_end_of_word and suffix:
            suffixes.append(suffix)
        for char, node in self.children.items():
            suffixes.extend(node.suffixes(suffix + char))
        return suffixes

# Define Trie
class Trie:
    def __init__(self):
        self.root = TrieNode()

    def insert(self, word):
        current_node = self.root
        for char in word:
            if char not in current_node.children:
                current_node.insert(char)
            current_node = current_node.children[char]
        current_node.is_end_of_word = True

    def find(self, prefix):
        current_node = self.root
        for char in prefix:
            if char not in current_node.children:
                return None
            current_node = current_node.children[char]
        return current_node

# Initialize and populate the Trie
MyTrie = Trie()
wordList = [
    "ant", "anthology", "antagonist", "antonym", 
    "fun", "function", "factory", 
    "trie", "trigger", "trigonometry", "tripod"
]
for word in wordList:
    MyTrie.insert(word)
def f(prefix):
    if prefix != '':
        prefixNode = MyTrie.find(prefix)
        if prefixNode:
            print('\n'.join(prefixNode.suffixes()))
        else:
            print(prefix + " not found")
    else:
        print('')
interact(f,prefix='');