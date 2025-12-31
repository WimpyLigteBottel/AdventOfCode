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

fn part1() {
    let input = get_input()[0].to_string();

    for x in 0..10_000_000 {
        let digest = md5::compute(format!("{}{}", input, x));
        let hex = format!("{:x}", digest);

        if hex.starts_with("00000") {
            println!("Answer: {}", x);
            break;
        }
    }
}

fn part2() {
    let input = get_input()[0].to_string();

    for x in 0..10_000_000 {
        let digest = md5::compute(format!("{}{}", input, x));
        let hex = format!("{:x}", digest);

        if hex.starts_with("000000") {
            println!("Answer: {}", x);
            break;
        }
    }
}

fn main() {
    part1();
    part2();
}
