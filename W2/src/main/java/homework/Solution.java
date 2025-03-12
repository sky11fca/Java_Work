package homework;

import java.util.Arrays;

/**
 * Object of type Solution to solve the assignment problem
 */
public class Solution
{
    /**
     * The given assignment problem
     */
    private Problem problem;

    /**
     * Object of type solution
     * @param problem Problem: The assignment problem
     */
    public Solution(Problem problem) {
        this.problem = problem;
    }

    /**
     * Algorithm to solve the student assignment
     * @return String[] the student and the assigned project based off the preference
     *
     * <p><strong>HOW DOES THIS WORK?</strong></p>
     * <p>Starting with 2 arrays, allocation of type String[] and allocatedProjects of type boolean[].</p>
     * <ul>
     *     <li>For each student</li>
     *     <li>For all the project the student desires to work on</li>
     *     <li>For all the given projects</li>
     *     <li>If the project the student desires is in the official project list, and the allocation wasn't done:</li>
     *     <li>The allocation array is updated with the name of the student and the project that is assigned</li>
     *     <li>Sets the allocation to true</li>
     *
     *     <li>Algorithm stops if it iterated to all students and projects</li>
     * </ul>
     *
     * <p><strong>HOW DOES THE GREEDY WORK IN THIS CASE</strong></p>
     * <p>The way the greedy is implemented is that for The list of prefered projects and all the projects will choose the first one that coincide.</p>
     * <p><strong>EXAMPLE</strong>: In the main program, student Jennifer Rose has the following projects: p1, p2 and John Pork has the p1 and p4. Because Jennifer is the first student She was assigned to do p1. John Pork cannot have p1 so he was assigned to do p4</p>
     *
     * <p><strong>TIME COMPLEXITY</strong></p>
     * <p>The method iterates through All the students (length of size n)</p>
     * <p>For each student it has a list of prefered projects (length of size k)</p>
     * <p>It goes through all possible projects length of size m</p>
     *
     * As such, the time complexity of the algorithm is O(n*m*k)
     */

    public String[] solver()
    {
        String[] allocation = new String[problem.getStudents().length];
        int projectsLength = problem.getProposedProjects().length;
        boolean[] allocatedProjects = new boolean[projectsLength];

        for (int i = 0; i < problem.getStudents().length; i++)
        {
            for(Project desiredProject : problem.getStudents()[i].getPreferedProjects())
            {
                for(int j = 0; j<problem.getProposedProjects().length; j++)
                {
                    if(problem.getProposedProjects()[j].equals(desiredProject) && !allocatedProjects[j])
                    {
                        allocation[i] = problem.getStudents()[i].getName() + " -> " + desiredProject.getName();
                        allocatedProjects[j] = true;
                        break;
                    }
                }
                if(allocation[i]!=null) break;
            }
        }

        return allocation;
    }


}