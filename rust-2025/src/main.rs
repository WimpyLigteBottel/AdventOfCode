mod day4;
mod util;

use crate::day4::day4::{part1, part2};
use crate::util::avg_time;
use util::read_results;

fn main() {
    let day = "day4";
    let path = "src/".to_owned() + day + "/input";
    let result = read_results(path.as_str());

    println!("{:?}", part1(&result).eq("1416"));
    println!("{:?}", part2(&result).eq("9086"));

    avg_time("part 1", 5, || {
        let _ = part1(&result).eq("1416");
    });

    avg_time("part 2", 5, || {
        let _ = part2(&result).eq("9086");
    });
}
