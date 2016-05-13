## Programming Assignment #3 for CSCI3320 - Advanced Programming
## Written by Justin Shapiro


towerA = []
towerB = []
towerC = []
move_number = 0

def getTowerLetter(_tower):
    if _tower == towerA:
        return 'A'
    elif _tower == towerB:
        return 'B'
    elif _tower == towerC:
        return 'C'

def printCurrentState():
    print "** Move Number: ", move_number

    towerA.reverse()
    towerB.reverse()
    towerC.reverse()

    print "A:", '[%s]' % ', '.join(map(str, towerA))
    print "B:", '[%s]' % ', '.join(map(str, towerB))
    print "C:", '[%s]' % ', '.join(map(str, towerC))

    towerA.reverse()
    towerB.reverse()
    towerC.reverse()

    print ""

def moveDisks(stack_size, source, destination, storage):
    if (stack_size == 1):

        destination.reverse()
        destination.append(source[0])
        destination.reverse()

        source.reverse()
        source.pop()
        source.reverse()

        global move_number
        move_number += 1

        _to = getTowerLetter(destination)
        _from = getTowerLetter(source)

        print _from, "->", _to
        printCurrentState()
    else:
        moveDisks(stack_size - 1, source, storage, destination)

        destination.reverse()
        destination.append(source[0])
        destination.reverse()

        source.reverse()
        source.pop()
        source.reverse()

        move_number += 1

        _to = getTowerLetter(destination)
        _from = getTowerLetter(source)

        print _from, "->", _to
        printCurrentState()

        moveDisks(stack_size - 1, storage, destination, source)

def main():
    num_disks = input("Enter number of disks: ")

    i = 1
    while i != num_disks + 1:
        towerA.append(i)
        i += 1

    printCurrentState()
    moveDisks(len(towerA), towerA, towerC, towerB)

    print "Number of Moves: ", move_number

    return 0

main()