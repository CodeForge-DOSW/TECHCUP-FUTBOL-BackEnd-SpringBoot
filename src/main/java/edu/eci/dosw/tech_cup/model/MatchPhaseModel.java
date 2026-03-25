package edu.eci.dosw.tech_cup.model;

/**
 * Define las fases en las que puede estar un partido dentro del torneo.
 *
 * Este enum permite clasificar los partidos según su etapa en la competencia,
 * como fase de grupos o eliminatorias, facilitando la organización y
 * generación de llaves.
 */
public enum MatchPhaseModel {

    /** Fase de grupos */
    GROUP_STAGE,

    /** Cuartos de final */
    QUARTERFINAL,

    /** Semifinal */
    SEMIFINAL,

    /** Final del torneo */
    FINAL;

    /**
     * Verifica si el partido pertenece a fase de grupos.
     *
     * @return true si es GROUP_STAGE
     */
    public boolean isGroupStage() { return this == GROUP_STAGE; }

    /**
     * Verifica si el partido pertenece a una fase eliminatoria.
     *
     * @return true si es QUARTERFINAL, SEMIFINAL o FINAL
     */
    public boolean isKnockoutStage() { return this == QUARTERFINAL || this == SEMIFINAL || this == FINAL; }

    /**
     * Verifica si el partido es una fase final.
     *
     * @return true si es FINAL
     */
    public boolean isFinal() { return this == FINAL; }

    /**
     * Verifica si el partido es semifinal.
     *
     * @return true si es SEMIFINAL
     */
    public boolean isSemifinal() { return this == SEMIFINAL; }

    /**
     * Verifica si el partido es de cuartos de final.
     *
     * @return true si es QUARTERFINAL
     */
    public boolean isQuarterfinal() { return this == QUARTERFINAL; }
}
