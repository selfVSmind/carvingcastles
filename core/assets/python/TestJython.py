__author__ = 'Jason Lambert'
__email__ = 'jason@jasonlambert.io'
__license__ = 'GPL3'

from com.selfvsmind.carvingcastles.jython import TestJythonType

class TestJython(TestJythonType):
    def say_hello(self):
        print "Hello CarvingCastles"

if __name__ == "__main__":
    test = TestJython()
    test.say_hello()
