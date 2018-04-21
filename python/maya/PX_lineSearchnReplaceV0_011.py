import sys
import os
import string
import time
'''
Removes the mental ray popup by removing the requirement line in .ma files.

Can be used on a folder and left on overnight

'''

def replaceLine(fName):
    #read file
    FileName = fName

    f = open(FileName,"r")
    outName = FileName+".new"
    outputFile = open(outName,"w")
    find = "oscar/ProjectX"

    for line in f.readlines():
        ln = line
        
        #if line has \\oscar
            #split along oscar
        if find in ln:
            #print "thingy detected"
            splitstr = ln.split("oscar/ProjectX")    
        #create new string using split[0] + "\\zodd\stuff" + split[1]
            newstr = splitstr[0]+"zod/ProjectX/_Backup"+splitstr[1]
            outputFile.write(newstr)
        else:
            outputFile.write(ln)
            #print "no thingy detected"
            

    #close files
    f.close()
    outputFile.close()

    print("Operation Completed")


def readDir():
    rootDir = os.getcwd()

    tDir = rootDir
    print "Selected Directory: %s\n" % tDir
    for dirpath, dirnames, filenames in os.walk(tDir):
        print "dirpath = ",dirpath
        print "dirnames = ",dirnames
        print "filenames = ",filenames
        #do stuff

    print "Operation Complete"

def replacestuff():
    rootDir = os.getcwd()

    tDir = rootDir
    print "Selected Directory: %s\n" % tDir
    for dirpath, dirnames, filenames in os.walk(tDir):
        #do stuff
        #print "dirpath = ",dirpath
        #print "dirnames = ",dirnames
        #print "filenames = ",filenames

        for i in range(0,len(filenames)):
            if filenames[i].endswith(".ma"):
                replaceLine(filenames[i])
        

    print "Operation Complete"

def cleanUp():
    #go through directory, delete .ma files and remove .new on the second pass
    rootDir = os.getcwd()

    tDir = rootDir
    print "Selected Directory: %s\n" % tDir
    for dirpath, dirnames, filenames in os.walk(tDir):
        for i in range(0,len(filenames)):
            if filenames[i].endswith(".ma"):
                os.remove(filenames[i])
            if filenames[i].endswith(".new"):
                oldname = filenames[i].split(".new")
                newname = oldname[0]
                os.rename(filenames[i],newname)
        #do stuff


    
start = time.time()

replacestuff()

cleanUp()

end = time.time()
print "Time Elapsed:",end - start
