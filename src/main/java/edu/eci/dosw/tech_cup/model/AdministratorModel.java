package edu.eci.dosw.tech_cup.model;

import java.util.List;

/**
 * Represents a system administrator.
 *
 * <p>This role is responsible for managing academic catalog elements such as
 * careers, as well as assigning roles to users within the system.</p>
 */
public class AdministratorModel extends UserRoleModel {

    /**
     * Changes the role of a user within the system.
     *
     * @param user user whose role will be changed
     * @param role new role to assign
     */
    public void changeUserRole(PlayerModel user, UserRoleModel role) {

    }

    /**
     * Creates a new academic program.
     *
     * @param name academic program name
     * @return new {@link CareerModel} instance
     */
    public CareerModel createCareer(String name) {
        return null;
    }

    /**
     * Updates the information of an existing academic program.
     *
     * @param career academic program to update
     */
    public void updateCareer(CareerModel career) {

    }

    /**
     * Deletes an academic program from the system.
     *
     * @param career academic program to delete
     */
    public void deleteCareer(CareerModel career) {

    }

    /**
     * Returns the list of all registered academic programs.
     *
     * @return list of academic programs
     */
    public List<CareerModel> getCareers() { return null; }

    /**
     * Finds an academic program by its name.
     *
     * @param name academic program name to search
     * @return matching academic program, or {@code null} if it does not exist
     */
    public CareerModel findCareerByName(String name) {
        return null;
    }

    /**
     * Indicates whether an academic program already exists in the system.
     *
     * @param name academic program name
     * @return {@code true} if the academic program exists
     */
    public boolean careerExists(String name) {
        return false;
    }

    /**
     * Assigns an academic program to a system user.
     *
     * @param user user receiving the academic program
     * @param career academic program to assign
     */
    public void assignCareerToUser(PlayerModel user, CareerModel career) {

    }

    /**
     * Removes the current role from a user.
     *
     * @param user user whose role will be removed
     */
    public void removeUserRole(PlayerModel user) {

    }

    /**
     * Returns users who have a specific role.
     *
     * @param role role to query
     * @return list of users with the requested role
     */
    public List<PlayerModel> getUsersByRole(UserRoleModel role) {
        return null;
    }
}
