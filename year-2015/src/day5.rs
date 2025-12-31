use std::fs::File;
use std::io::Read;

fn get_input() -> Vec<String> {
    let mut buffer = String::new();
    let mut f = File::open("src/input.txt").expect("Unable to open file");
    f.read_to_string(&mut buffer).expect("Unable to read file");

    buffer
        .lines() // splits on \n or \r\n automatically
        .map(|s| s.to_string()) // convert &str → String
        .collect()
}

fn part1() -> String {
    fn contains_vowels(input: String) -> bool {
        let mut counter = 0;

        for x in 0..input.len() {
            let slice = input[x..x + 1].to_string();

            for y in ["a", "e", "i", "o", "u"] {
                if slice == y {
                    counter += 1
                }
            }
        }

        counter >= 3
    }

    fn contains_2letters_in_row(input: String) -> bool {
        let mut previous = '?';
        for x in input.chars() {
            if x == previous {
                return true;
            }
            previous = x
        }

        false
    }

    fn not_contains(input: String) -> bool {
        let mut counter = 0;
        for x in 0..input.clone().len() - 1 {
            let slice = input[x..x + 2].to_string();

            for y in ["ab", "cd", "pq", "xy"].to_vec() {
                if slice.eq(y) {
                    counter += 1
                }
            }
        }

        counter == 0
    }
    let mut counter = 0;

    for input in get_input() {
        let has_letters = contains_vowels(input.to_string());
        let contains_2_letters = contains_2letters_in_row(input.to_string());
        let not_contains = not_contains(input.clone());
        if not_contains && contains_2_letters && has_letters {
            counter += 1;
        }
    }

    counter.to_string()
}

fn part2() -> String {
    fn contains_2letters(input: String) -> bool {
        let mut previous = '?';
        let mut pp = '?';
        for current in input.chars() {
            if current == pp {
                return true;
            }
            pp = previous;
            previous = current;
        }

        false
    }
    fn contains_repeating(input: String) -> bool {
        let bytes = input.as_bytes();

        for i in 0..bytes.len() - 1 {
            let pair = &bytes[i..i + 2];

            for j in i + 2..bytes.len() - 1 {
                if pair == &bytes[j..j + 2] {
                    return true;
                }
            }
        }

        false
    }

    let mut counter = 0;

    for input in get_input() {
        let contains_2_letters = contains_2letters(input.to_string());
        let contains_repeating = contains_repeating(input.to_string());

        if contains_2_letters && contains_repeating {
            counter += 1;
        }
    }

    counter.to_string()
}

fn main() {
    println!("{}", part1()); // 236
    println!("{}", part2()); // 51
}
