import os

def find_files(suffix: str, path: str) -> list[str]:
    """
    Find all files beneath path with file name suffix.

    Note that a path may contain further subdirectories
    and those subdirectories may also contain further subdirectories.

    There are no limit to the depth of the subdirectories can be.

    Parameters:
    -----------
    suffix : str
        The suffix of the files to be found.
    path : str
        The root directory path where the search should begin.

    Returns:
    --------
    list[str]
        A list of file paths that end with the given suffix.
    """
    output = list()
    def find_files_out(suffix,path,output):
        if suffix is None:
            return []
        if not os.path.exists(path):
            raise FileNotFoundError(f"Error: The path '{path}' does not exist.")
        if os.listdir(path) == []:
            # Empty Directory
            return []
        paths = os.listdir(path)
        # print (paths)
        for p in paths:
            p=os.path.join(path,p)
            if os.path.isdir(p):
                # print(p)
                find_files_out(suffix,p,output)
            elif os.path.isfile(p):
                if p.endswith(suffix):
                    # print(p)
                    output.append(p)
                    # print (output)


    find_files_out(suffix,path,output)

    return output
    


if __name__ == "__main__":
    # Test Case 1: Standard test case with known structure
    print("Test Case 1: Standard directory structure")
    result = find_files(".c", "./testdir")
    print(f"Files with .c are : {result}")
    # Expected output: ['./testdir/subdir1/a.c', './testdir/subdir3/subsubdir1/b.c', './testdir/subdir5/a.c', './testdir/t1.c']

    # Test Case 2 another extension (.h)
    result = find_files(".h", "./testdir")
    print(f"Files with .h are : {result}")
   
    # Test Case 3 : extension not found : return empty list
    result = find_files(".haha", "./testdir")
    print(f"Files with .h are : {result}")

    # Test Case 4 : path not found : return error
    try:
        result = find_files(".c", "./testdiiiiii")
        print(f"Files with .c are: {result}")
    except Exception as e:
        print(f"Error in Test Case 4: {e}")
 
    # Test Case  5 : path has empty folders: return the same list as TC1 
    result = find_files(".c", "./testdir")
    print(f"Files with .c are : {result}")



