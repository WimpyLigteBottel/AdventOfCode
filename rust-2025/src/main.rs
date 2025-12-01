mod util;

use std::collections::VecDeque;
use std::ops::Mul;
use util::read_results;

fn main() {
    let path = "src/day1/input.txt";
    let result = read_results(path);

    println!("{}", day1(&result).eq("1086"));
    println!("{}", day2(&result).eq("6268"));
}

fn day1(result: &Vec<String>) -> String {
    let mut dial = 50;
    let mut counter = 0;

    result.iter().for_each(|s| {
        // gets the direction
        let direction = &s[0..1];
        // the amount of turns (i am % 100 to get only turns that are necessary for performance)
        let turns = s[1..].parse::<i32>().expect("Couldn't parse number") % 100;
        dial = perform_turn(dial, direction, turns);
        if (dial == 0) {
            counter += 1
        }
    });

    counter.to_string()
}


/// # Description
/// Will loop x times in specified direction the return the result back.
///
/// If dial goes over 99 then it goes to 0
/// if dial goes below 0 then it goes to 99
///
/// # Arguments
///
/// * `old_dial`: the starting number
/// * `direction`: determines if it should add or remove
/// * `turns`: how many times you need to repeat
///
/// returns: i32
///
/// # Examples
///
/// Example 1
/// ```
/// dial == 0
/// direction = R
/// turns = 1
///
/// result == 1
/// ```
///
/// Example: 2
/// ```
/// dial == 99
/// direction = R
/// turns = 1
///
/// result = 0
/// ```
/// Example: 3
/// ```
/// dial == 0
/// direction = L
/// turns = 1
///
/// result = 99
/// ```
fn perform_turn(old_dial: i32, direction: &str, turns: i32) -> i32 {
    let mut dial = old_dial;
    match (direction) {
        "L" => {
            for _ in 0..turns {
                dial -= 1;
                if (dial < 0) {
                    dial = 99
                }
            }
        }
        "R" => {
            for _ in 0..turns {
                dial += 1;
                if (dial > 99) {
                    dial = 0
                }
            }
        }
        _ => {}
    }
    dial
}

fn day2(result: &Vec<String>) -> String {
    let mut dial = 50;
    let mut counter = 0;

    result.iter().for_each(|s| {
        // gets the direction
        let direction = &s[0..1];
        // the amount of turns (i am % 100 to get only turns that are necessary for performance)
        let turns = s[1..].parse::<i32>().expect("Couldn't parse number") % 100;
        // if full rotation is made then it has landed on 0
        counter += s[1..].parse::<i32>().expect("Couldn't parse number") / 100;

        match (direction) {
            "L" => {
                for _ in 0..turns {
                    dial -= 1;
                    if (dial < 0) {
                        dial = 99
                    }
                    if (dial == 0) {
                        counter += 1
                    }
                }
            }
            "R" => {
                for _ in 0..turns {
                    dial += 1;

                    if (dial == 100) {
                        dial = 0
                    }
                    if (dial == 0) {
                        counter += 1
                    }
                }
            }
            _ => {}
        }
    });

    counter.to_string()
}
