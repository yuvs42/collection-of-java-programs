class Csp:
    # constractor fot the Csp class
    def __init__(self, variables, domains, neighbors, constraints):
        self.variables = variables
        self.domains = domains
        self.neighbors = neighbors
        self.constraints = constraints
        self.current_domains = None
        self.number_of_variables = len(variables)

    # function to assign values that consist with the assignments
    def assign(self, variable, value, assignment):
        assignment[variable] = value

    # function to remove values form the assignments
    def remove(self, variable, assignment):
        if variable in assignment:
            del assignment[variable]

    # function to check is the new variable is consistent with the assignments
    def isConsistent(self, variable, value, assignment):
        for v in self.neighbors[variable]:
            if v in assignment and not (value != assignment[v]):
                return False
        return True

    # function to make new inferences regarding the search
    def forwardChecking(self, csp, variable, value, assignment, removals):
        csp.support_trimming()
        for A in csp.neighbors[variable]:
            if A not in assignment:
                for b in csp.current_domains[A][:]:
                    if not csp.constraints(variable, value, A, b):
                        csp.trim(A, b, removals)
                if not csp.current_domains[A]:
                    return False
        return True

    def trim(self, variable, value, removals):
        self.current_domains[variable].remove(value)
        if removals is not None:
            removals.append((variable, value))

    # a function to retrieve the next node in the graph to explore
    def firstVariable(self, assignment, csp):
        for var in csp.variables:
            if var not in assignment:
                return var

    # a function to retrieve possible domains for assignment
    def support_trimming(self):
        if self.current_domains is None:
            self.current_domains = {v: list(self.domains[v]) for v in self.variables}

    # a function to make inferences about future assignments
    def assumption(self, variable, value):
        removals = []
        self.support_trimming()
        for a in self.current_domains[variable]:
            if a != value:
                removals = [variable, a]
        self.current_domains[variable] = [value]
        return removals

    # a function to retrieve possible domain values
    def domainValues(self, variable):
        return (self.current_domains or self.domains)[variable]

    def backtrack(self, assignment, csp):
        if len(assignment) == len(variables):
            return assignment
        variable = csp.firstVariable(assignment, csp)
        for value in self.domainValues(variable):
            if self.isConsistent(variable, value, assignment):
                csp.assign(variable, value, assignment)
                rem = csp.assumption(variable, value)
                if csp.forwardChecking(csp, variable, value, assignment, rem):
                    results = self.backtrack(assignment, csp)
                    if results is not None:
                        return results
            csp.remove(variable, assignment)
        return None


variables = []
domains = {}
domain = ['Red', 'Green', 'Blue', 'Yellow', 'Violet', 'Gray', 'Orange']
adj_vertex = {}
constraints = {}
assignments = {}

# Reading the file, and inserting the nodes names into the variables list, and setting the adjecent matrix,
# which will represent the graph.
f = open("graph.txt", 'r')
number_of_vertex = int(f.readline())
if number_of_vertex in range(2, 20):
    pass
else:
    print('The program only deal with graph the size of 2 to 20')
    print('please adjust the graph accordingly')
    print('program will terminate')
    exit()
# reading the graph.txt file abd inserting the information into the variables
for i in range(number_of_vertex):
    a = f.readline()
    astr = a.replace(" ", "")
    variables.append(astr[0])
    adj_vertex.setdefault(astr[0], [])
    domains.setdefault(astr[0], [])
    for x in range(1, len(astr) - 1):
        adj_vertex[astr[0]].append(astr[x])

f.close()
# setting possible domain values to each node
for key in variables:
    domains[key] = domain


# a function to define new constraint
def mapConstraint(A, a, B, b):
    return a != b


# a function to print the solution
def printSolution(assignment):
    for key, value in assignment.items():
        print('Node: ' + key + ', color: ' + value)

# creating an object of type Csp
solution = Csp(variables, domains, adj_vertex, mapConstraint)
# calling the backtrack function
assignments = solution.backtrack({}, solution)
if assignments == None:
    print('Not possible with 7 colors')
else:
    printSolution(assignments)
