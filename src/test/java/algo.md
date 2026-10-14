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