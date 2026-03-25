package edu.eci.dosw.tech_cup.model;

/**
 * Define los tipos de formaciones tácticas que puede utilizar un equipo
 * dentro del sistema.
 *
 * Cada valor representa una distribución específica de jugadores en el campo,
 * la cual es utilizada por LineupModel para validar la alineación.
 */
public enum FormationTypeModel {

    /** Formación 3-2-1 */
    THREE_TWO_ONE,

    /** Formación 2-2-2 */
    TWO_TWO_TWO,

    /** Formación 1-4-1 */
    ONE_FOUR_ONE,

    /** Formación 2-3-1 */
    TWO_THREE_ONE,

    /** Formación 3-1-2 */
    THREE_ONE_TWO,

    /** Formación 2-1-3 */
    TWO_ONE_THREE,

    /** Formación 1-3-2 */
    ONE_THREE_TWO,

    /** Formación 0-4-3 */
    ZERO_FOUR_THREE,

    /** Formación 2-4-0 */
    TWO_FOUR_ZERO,

    /** Formación 3-3-0 */
    THREE_THREE_ZERO,

    /** Formación 4-1-1 */
    FOUR_ONE_ONE,

    /** Formación 4-2-0 */
    FOUR_TWO_ZERO,

    /** Formación 5-1-0 */
    FIVE_ONE_ZERO,

    /** Formación 0-5-1 */
    ZERO_FIVE_ONE,

    /** Formación 1-2-3 */
    ONE_TWO_THREE;
}
