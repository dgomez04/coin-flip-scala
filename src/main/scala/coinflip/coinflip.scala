package coinflip

import Utils._ 
import scala.annotation.tailrec
import scala.util.Random


case class Game(num_flips: Int, num_correct: Int)
case class GameState(games: List[Game])

object CoinFlip extends App {
    val r = Random
    val state = GameState(List(Game(0, 0)))
    main_loop(state, r)

    @tailrec
    def main_loop(game_state : GameState, random : Random) : Unit = {
        show_prompt()
        val input = get_input()
        val current_game = game_state.games.head


        input match {
            // play round
            case "h" | "t" => {
                val toss = coin_toss(random)
                
                val new_game = if(input == toss) {
                    current_game.copy(num_flips = current_game.num_flips + 1, num_correct = current_game.num_correct + 1)
                } else { 
                    current_game.copy(num_flips = current_game.num_flips + 1)
                }
                
                val new_state = game_state.copy(games = new_game :: game_state.games.tail)
                print_results(toss_result(toss), new_state)
                main_loop(new_state, random)
            }

            // quit game
            case "q" => {
                print_over()
                print_state(current_game)
            }

            // create a (n)ew game 
            case "n" => {
                val current_state = GameState(Game(0, 0) :: game_state.games)
                main_loop(current_state, random)
            }

            case "p" => {
                print_games(game_state)
                main_loop(game_state, random)
            }

            // every other case
            case _ => {
                println("\ninvalid input")
                main_loop(game_state, random)
            }
        }
    }
}