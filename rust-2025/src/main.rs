mod day3;
mod util;

use crate::day3::day3::{part1, part2};
use crate::util::avg_time;
use util::read_results;

fn main() {
    let path = "src/day3/input";
    let result = read_results(path);

    println!("{:?}", part1(&result).eq("17100"));
    println!("{:?}", part2(&result).eq("170418192256861"));

    avg_time("day3 part 1", 5, || {
        let _ = part1(&result).eq("17100");
    });

    avg_time("day3 part 2", 5, || {
        let _ = part2(&result).eq("170418192256861");
    });
}
