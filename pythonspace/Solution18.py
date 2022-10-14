# kakao blind 5번

def solution(commands):
    answer = []
    table = []
    for i in range(50):
        table.append(["EMPTY" for i in range(50)])

    mergeList = []
    for i in range(len(commands)):
        divCommand = commands[i].split(" ")
        # UPDATE
        if divCommand[0] == "UPDATE":
            if len(divCommand) > 3:
                if len(mergeList) == 0:
                    table[int(divCommand[1])][int(divCommand[2])] = divCommand[3]
                else:
                    for mergeElement in mergeList:
                        if divCommand[1]+divCommand[2] in mergeElement:
                            for e in mergeElement:
                                table[int(e[0])][int(e[1])] = divCommand[3]
                        else:
                            table[int(divCommand[1])][int(divCommand[2])] = divCommand[3]
            else:
                for j in range(i):
                    if divCommand[1] in commands[j]:
                        _divCommand = commands[j].split(" ")
                        table[int(_divCommand[1])][int(_divCommand[2])] = divCommand[2]
        # MERGE
        if divCommand[0] == "MERGE":
            temp = table[int(divCommand[1])][int(divCommand[2])]
            changeCell = divCommand[1]+divCommand[2]
            baseCell = divCommand[3]+divCommand[4]
            
            if len(mergeList) == 0:
                table[int(divCommand[3])][int(divCommand[4])] = temp
                mergeList.append([changeCell, baseCell])
            else:
                for mergeElement in mergeList:
                    if changeCell in mergeElement:
                        for e in mergeElement:
                            table[int(e[0])][int(e[1])] = temp
                    else:
                        table[int(divCommand[3])][int(divCommand[4])] = temp
            # mergeList에 추가
            for i in range(len(mergeList)):
                if changeCell in mergeList[i] and baseCell not in mergeList[i]:
                    mergeList[i].append(baseCell)
                elif baseCell in mergeList[i] and changeCell not in mergeList[i]:
                    mergeList[i].append(changeCell)
                elif changeCell not in mergeList[i] and baseCell not in mergeList[i]:
                    mergeList.append([changeCell, baseCell])
        # UNMERGE
        if divCommand[0] == "UNMERGE":
            for i in range(len(mergeList)):
                if divCommand[1]+divCommand[2] in mergeList[i]:
                    for j in range(len(mergeList[i])):
                        if divCommand[1]+divCommand[2] != mergeList[i][j]:
                            table[int(mergeList[i][j][0])][int(mergeList[i][j][1])] = "EMPTY"
                    del mergeList[i]
        # PRINT        
        if divCommand[0] == "PRINT":
            answer.append(table[int(divCommand[1])][int(divCommand[2])])

    return answer

# result => ["EMPTY", "group"]
commands = ["UPDATE 1 1 menu", "UPDATE 1 2 category", "UPDATE 2 1 bibimbap", "UPDATE 2 2 korean", "UPDATE 2 3 rice", "UPDATE 3 1 ramyeon", "UPDATE 3 2 korean", "UPDATE 3 3 noodle", "UPDATE 3 4 instant", "UPDATE 4 1 pasta", "UPDATE 4 2 italian", "UPDATE 4 3 noodle", "MERGE 1 2 1 3", "MERGE 1 3 1 4", "UPDATE korean hansik", "UPDATE 1 3 group", "UNMERGE 1 4", "PRINT 1 3", "PRINT 1 4"]

# result => ["d", "EMPTY"]
# commands = ["UPDATE 1 1 a", "UPDATE 1 2 b", "UPDATE 2 1 c", "UPDATE 2 2 d", "MERGE 1 1 1 2", "MERGE 2 2 2 1", "MERGE 2 1 1 1", "PRINT 1 1", "UNMERGE 2 2", "PRINT 1 1"]

print(solution(commands))