package com.example.fightorgaapp.data.model.shared

data class FighterStatistics(
    val totalPoints: Int = 0,
    val totalWins: Int = 0,
    val totalFights: Int = 0,
    val winRate: Float = 0f
) {
    companion object {
        fun calculate(fights: List<JJBFight>, fighterId: Long): FighterStatistics {
            val totalFights = fights.count { it.fighter1Id == fighterId || it.fighter2Id == fighterId }
            if (totalFights == 0) return FighterStatistics()

            val wins = fights.count { fight ->
                when {
                    fight.fighter1Id == fighterId && fight.result == FightResult.FIGHTER1_WIN -> true
                    fight.fighter2Id == fighterId && fight.result == FightResult.FIGHTER2_WIN -> true
                    else -> false
                }
            }

            val points = fights.sumOf { fight ->
                when {
                    fight.fighter1Id == fighterId -> fight.fighter1Points
                    fight.fighter2Id == fighterId -> fight.fighter2Points
                    else -> 0
                }
            }

            return FighterStatistics(
                totalPoints = points,
                totalWins = wins,
                totalFights = totalFights,
                winRate = if (totalFights > 0) wins.toFloat() / totalFights else 0f
            )
        }
    }
} 