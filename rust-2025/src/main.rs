mod day7;
mod util;

use crate::day7::day7::{part1, part2};
use crate::util::avg_time;
use util::read_results;

fn main() {
    let day = "day7";
    let path = "src/".to_owned() + day + "/input";
    let result = read_results(path.as_str());

    let answer1 = "1537";
    let answer2 = "18818811755665";

    println!("{:?}", part1(&result).eq(answer1));
    println!("{:?}", part2(&result).eq(answer2));

    avg(result, answer1, answer2);
}

fn avg(result: Vec<String>, answer1: &str, answer2: &str) {
    avg_time("part 1", 5, || {
        let _ = part1(&result).eq(answer1);
    });

    avg_time("part 2", 5, || {
        let _ = part2(&result).eq(answer2);
    });
}
