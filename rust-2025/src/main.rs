mod day2;
mod util;

use crate::day2::day2::{part1, part2};
use crate::util::avg_time;
use util::read_results;

fn main() {
    let path = "src/day2/input.txt";
    let result = read_results(path);

    println!("{:?}", part1(&result).eq("23534117921"));
    println!("{:?}", part2(&result).eq("31755323497"));

    avg_time("day1 part 1", 5, || {
        let _ = part1(&result).eq("23534117921");
    });

    avg_time("day1 part 2", 5, || {
        let _ = part2(&result).eq("31755323497");
    });
}
