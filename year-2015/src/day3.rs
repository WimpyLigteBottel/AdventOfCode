use std::collections::HashSet;
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

#[derive(Eq, PartialEq, Hash, Clone, Copy)]
struct Point {
    x: i32,
    y: i32,
}

fn calculate_point(current: &str, point: Point) -> Point {
    let mut point = point.clone();

    match current {
        "^" => point.y += 1,
        "v" => point.y -= 1,
        ">" => point.x += 1,
        "<" => point.x -= 1,
        _ => {}
    }
    point
}

fn part1() -> usize {
    let input: String = get_input()[0].to_string();

    let mut visited: HashSet<Point> = HashSet::new();

    let mut santa = Point { x: 0, y: 0 };

    visited.insert(Point { x: 0, y: 0 });

    for x in 0..input.len() {
        let direction = input[x..x + 1].to_string();
        santa = calculate_point(direction.as_str(), santa);

        visited.insert(santa);
    }

    visited.len()
}

fn part2() -> usize {
    let input: String = get_input()[0].to_string();

    let mut visited: HashSet<Point> = HashSet::new();

    let mut santa = Point { x: 0, y: 0 };
    let mut robot = Point { x: 0, y: 0 };

    visited.insert(santa);

    for x in 0..input.len() {
        let direction = input[x..x + 1].to_string();
        if x.rem_euclid(2) == 1 {
            santa = calculate_point(direction.as_str(), santa);
        } else {
            robot = calculate_point(direction.as_str(), robot);
        }

        visited.insert(robot.clone());
        visited.insert(santa.clone());
    }

    visited.len()
}

fn main() {
    println!("{}", part1()); // 2592
    println!("{}", part2()); // 2360
}
