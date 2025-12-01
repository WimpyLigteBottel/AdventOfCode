mod day1;
mod util;

use crate::day1::day1::{day1, day2};
use crate::util::avg_time;
use util::read_results;

fn main() {
    let path = "src/day1/input.txt";
    let result = read_results(path);

    println!("{:?}", day1(&result).eq("1086"));
    println!("{:?}", day2(&result).eq("6268"));

    avg_time("day1 part 1", 5, || {
        let _ = day1(&result).eq("1086");
    });

    avg_time("day1 part 2", 5, || {
        let _ = day2(&result).eq("6268");
    });
}
