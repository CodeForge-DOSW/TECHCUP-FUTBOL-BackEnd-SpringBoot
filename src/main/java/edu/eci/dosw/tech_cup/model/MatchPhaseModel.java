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

    // ===================== MÉTODOS =====================

    /**
     * Verifica si el partido pertenece a fase de grupos.
     *
     * @return true si es GROUP_STAGE
     */
    public boolean isGroupStage() { return false; }

    /**
     * Verifica si el partido pertenece a una fase eliminatoria.
     *
     * @return true si es QUARTERFINAL, SEMIFINAL o FINAL
     */
    public boolean isKnockoutStage() { return false; }

    /**
     * Verifica si el partido es una fase final.
     *
     * @return true si es FINAL
     */
    public boolean isFinal() { return false; }

    /**
     * Verifica si el partido es semifinal.
     *
     * @return true si es SEMIFINAL
     */
    public boolean isSemifinal() { return false; }

    /**
     * Verifica si el partido es de cuartos de final.
     *
     * @return true si es QUARTERFINAL
     */
    public boolean isQuarterfinal() { return false; }
}
