import maya.cmds as cmds
"""
Allows for selecting color items to be quickly switched


0.01 - Initial prerelease

------

KNOWN BUGS:

Creates a new input node rather than changing the last one, preventing repeated color changes
-caused by the change target to be denoted as 'input[2]' or higher despite only 2 elements in the node
-possible fix: rather than use size of array, get array of inputs, decompose to array of ints, get largest number
-look at the crap you get when you search 'attr'
------
TODO/RECOMMENDED:
Color widget should change to color of selected on selection

List items could use labels

------
"""


#creates the color editor dialog, should pop up when color is selected
#cmds.colorEditor()


def assignColor(*args):
    col = cmds.colorInputWidgetGrp("colorWgt", q=True, rgb=True)
    thing = cmds.textScrollList( "colorItems", q=True, si=True)
    thingStr = str(thing)
    selected = thingStr.split("'")[1]
    
    colpos = cmds.getAttr(selected+".inputs",size=True)
    posStr = str(colpos-1)
    print posStr
    #setAttr [thingy]ArithmeticError.inputs[num].color type=True double3=True num1 num2 num3
    cmds.setAttr(selected+".inputs["+posStr+"].color",col[0],col[1],col[2],type='double3')

def itemSelect(*args):
    thing = cmds.textScrollList( "colorItems", q=True, si=True)
    thingStr = str(thing)
    cmds.text("activeObj",e=True, l=thingStr)
    



def appendList(*args):
    slct = cmds.ls(sl=True)
    item = slct[0]
    cmds.textScrollList( "colorItems", e=True, append=item)
    
def removeList(*args):
    thing = cmds.textScrollList( "colorItems", q=True, si=True)
    cmds.textScrollList( "colorItems", e=True, ri=thing)
    
def uiInit():

    wd = 400
    
    if cmds.window("ColorPicker", exists = True):
        cmds.deleteUI("ColorPicker")

        

    cmds.window("ColorPicker",title = "Quick Color Picker", w = wd, h = 300, s = False)

    cmds.columnLayout()
    cmds.text("activeObj",l="No Object Selected")
    cmds.colorInputWidgetGrp("colorWgt")
    cmds.button (l = "Apply", w = wd, c='assignColor()')
    cmds.textScrollList( "colorItems",w = wd, sc='itemSelect()')
    #on selection of thing: set active object
    
    cmds.rowLayout(numberOfColumns=2,columnWidth2=[(wd/2), (wd/2)], columnAlign2=["center", "center"])
    cmds.button (l = "Add", w=(wd/2), h=30, c='appendList()')
    #append item         
    cmds.button (l = "Remove", w=(wd/2), h=30, c='removeList()')
    #remove selected item                

    
    cmds.showWindow("ColorPicker")
    
uiInit()
