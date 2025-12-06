mod day6;
mod util;

use crate::day6::day6::{part1, part2};
use crate::util::avg_time;
use util::read_results;

fn main() {
    let day = "day6";
    let path = "src/".to_owned() + day + "/input";
    let result = read_results(path.as_str());

    println!("{:?}", part1(&result).eq("6343365546996"));
    println!("{:?}", part2(&result).eq("11136895955912"));

    avg_time("part 1", 5, || {
        let _ = part1(&result).eq("6343365546996");
    });

    avg_time("part 2", 5, || {
        let _ = part2(&result).eq("11136895955912");
    });
}


/*

   @Test
    fun avgPart1() {
        MarcoUtil.avgTime("part 1", 5) {
            assertEquals("6343365546996", day.answerOne())
        }

        MarcoUtil.avgTime("part 2", 5) {
            assertEquals("11136895955912", day.answerTwo())
        }
    }
 */