pub(crate) fn part1(result: &Vec<String>) -> String {
    let mut dial = 50;
    let mut counter = 0;

    result.iter().for_each(|s| {
        let (direction, turns) = get_direction_and_turns(&s); // gets the direction
        dial = perform_turn(dial, &direction, turns).0;
        if dial == 0 {
            counter += 1
        }
    });

    counter.to_string()
}

pub(crate) fn part2(result: &Vec<String>) -> String {
    let mut dial = 50;
    let mut counter = 0;

    result.iter().for_each(|s| {
        let (direction, turns) = get_direction_and_turns(&s); // gets the direction
        counter += s[1..].parse::<i32>().expect("Couldn't parse number") / 100; // if full rotation is made then it has landed on 0

        let (new_dial, should_increment) = perform_turn(dial, &direction, turns);

        dial = new_dial;

        if should_increment {
            counter += 1;
        }
    });
    counter.to_string()
}

fn get_direction_and_turns(s: &String) -> (String, i32) {
    let direction = &s[0..1]; // gets the direction
    let turns = s[1..].parse::<i32>().expect("Couldn't parse number") % 100; // the amount of turns (i am % 100 to get only turns that are necessary for performance)

    (direction.to_string(), turns)
}

/// # Description
/// Will loop x times in specified direction then return the result back and informs if it made a loop.
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
/// returns: (i32,bool)
///
/// # Examples
///
/// Example 1
/// ```
/// dial == 0
/// direction = R
/// turns = 1
///
/// result == (1, false)
/// ```
///
/// Example: 2
/// ```
/// dial == 99
/// direction = R
/// turns = 1
///
/// result = (0, true)
/// ```
/// Example: 3
/// ```
/// dial == 0
/// direction = L
/// turns = 1
///
/// result = (99,false)
/// ```
fn perform_turn(old_dial: i32, direction: &String, turns: i32) -> (i32, bool) {
    let mut dial = old_dial;
    let mut counter = false;
    match direction.as_str() {
        "L" => {
            for _ in 0..turns {
                dial -= 1;
                if dial == 0 {
                    counter = true;
                }
                if dial < 0 {
                    dial = 99
                }
            }
        }
        "R" => {
            for _ in 0..turns {
                dial += 1;
                if dial > 99 {
                    dial = 0;
                    counter = true;
                }
            }
        }
        _ => {}
    }
    (dial, counter)
}
