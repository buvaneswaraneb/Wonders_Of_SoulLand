A_STAR(start, goal):

    openSet = priority queue ordered by lowest fCost
    openSet.add(start)

    gCost[start] = 0
    fCost[start] = heuristic(start, goal)

    while openSet is not empty:

        current = node with lowest fCost in openSet

        if current == goal:
            return reconstructPath(current)

        remove current from openSet

        for each neighbor of current:

            tentativeG = gCost[current] + cost(current, neighbor)

            if tentativeG < gCost[neighbor]:

                neighbor.parent = current
                gCost[neighbor] = tentativeG
                hCost[neighbor] = heuristic(neighbor, goal)
                fCost[neighbor] = gCost[neighbor] + hCost[neighbor]

                if neighbor is not in openSet:
                    openSet.add(neighbor)

    return NO_PATH

- [x]  -1
- []  133
- []  134
- []  135
- []  136
- []  8
- []  10
- []  150
- []  151
- []  152
- []  158
- []  159
- []  160
- []  161
- []  33
- []  35
- []  36
- []  175
- []  176
- []  177
- []  305
- []  306
- []  183
- []  184
- []  185
- []  186
- []  58
- []  61
- []  200
- []  201
- []  202
- []  330
- []  331
- []  208
- []  209
- []  210
- []  83
- []  211
- []  84
- []  86
- []  225
- []  229
- []  233
- []  234
- []  235
- []  108
- []  236
- []  109
- []  110
- []  111
- []  254