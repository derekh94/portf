import maya.cmds as cmds
'''
Goes through selected items and checks if translates, rotates, and scales are 
identity, changing them to identity if they are not identity and not locked.


Created by Derek Ho

'''

def CheckItem(a , b):
    #if not identity, and not locked, add into a list
    
    #check if string contains scale
    #if scale
    if(a.find("scale") != -1):
        #print "S detected"
        if (cmds.getAttr(a)!=1.0) and (cmds.getAttr(a,lock=True)==False):
            b = b + [a]
    #if not, it's translate or rotate
    #if translate or rotate
    else:
        #print "TR detected"
        if(cmds.getAttr(a)!=0.0) and (cmds.getAttr(a,lock=True)==False):
            b = b + [a]

    return b
       
def SetItem(a):
    #if scale
    if(a.find("scale") != -1):
        #print "Setting S"
        cmds.setAttr(a, 1.0)
    #if translate/rotate
    else:
        cmds.setAttr(a, 0.0)
        #print "Setting TR"

def IdentityChecker():
    #selected
    selected = cmds.ls(selection=True)

    #attribute array
    attrArr = []

    #things to change
    toChange = []
  
    for i in range(0,len(selected)):
    #for every item in selected
        
        #go through attributes
        attrArr = attrArr+[selected[i]+'.translateX']
        attrArr = attrArr+[selected[i]+'.translateY']
        attrArr = attrArr+[selected[i]+'.translateZ']

        attrArr = attrArr+[selected[i]+'.rotateX']
        attrArr = attrArr+[selected[i]+'.rotateY']
        attrArr = attrArr+[selected[i]+'.rotateZ']

        attrArr = attrArr+[selected[i]+'.scaleX']
        attrArr = attrArr+[selected[i]+'.scaleY']
        attrArr = attrArr+[selected[i]+'.scaleZ']
        
        
        #check if identity
        for i in range(0,len(attrArr)):
            toChange = CheckItem(attrArr[i],toChange)

        #print "toChange = ", toChange
            
        #for every item in the !identity list
        #make identity
        for i in range(0,len(toChange)):
            SetItem(toChange[i])
            
            
IdentityChecker()
