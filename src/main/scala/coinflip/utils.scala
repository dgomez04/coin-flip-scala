package coinflip

import scala.util.Random 
import scala.io.StdIn.readLine

object Utils { 
    // print initial instructions
    def show_prompt () : Unit = { println("\n(h)eads, (t)ails, (n)ew game, (p)rint game history or (q)uit")}

    // get user input
    def get_input() : String = readLine.trim.toLowerCase

    // print flip results 
    def toss_result(toss: String) : String = toss match {
        case "h" => "heads"
        case "t" => "tails"
    } 

    // game state with flip result
    def print_results(toss_result : String, game_state: GameState) : Unit = {
        println(s"flip was $toss_result")
        print_state(game_state.games.head)
    } 
    
    // final game state
    def print_state(game: Game) : Unit = { println(s" num flips: ${game.num_flips}, num correct: ${game.num_correct}") }

    // print game over
    def print_over() : Unit = { println("\n~~~ game over ~~~") } 

    // print game history
    def print_games(game_state: GameState) : Unit = {
        println("\ngame history:")
        game_state.games.reverse.foreach { game => print_state(game) }
    }

    // get random flip result
    def coin_toss(r : Random) : String = {
        r.nextInt(2) match {
            case 0 => "h"
            case 1 => "t"
        }
    }
}