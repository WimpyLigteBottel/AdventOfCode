#[derive(Debug, Clone, Copy)]
struct Range(i128, i128);

// part 1 took 0.663 ms average (totalRuns=7546; inSeconds=5)
pub(crate) fn part1(result: &[String]) -> String {
    // Get ids
    let ids = result
        .iter()
        .filter(|id| !id.is_empty() && !id.contains("-"))
        .map(|id| id.parse().expect("valid id was expected"))
        .collect::<Vec<i64>>();

    // Get ranges
    let ranges = result
        .iter()
        .filter(|id| id.contains("-"))
        .map(|x| {
            let split: Vec<String> = x.split("-").map(|x| x.to_string()).collect();
            (split[0].clone(), split[1].clone())
        })
        .map(|(a, b)| {
            Range(
                a.parse().expect("Expected a number for A"),
                b.parse().expect("Expected a number for B"),
            )
        })
        .collect::<Vec<Range>>();

    let ranges = mergeRanges(ranges);

    let fresh_count = ids
        .iter()
        .filter(|id| {
            ranges
                .iter()
                .any(|range| range.0 <= **id as i128 && range.1 >= **id as i128)
        })
        .count();

    fresh_count.to_string()
}

// part 2 took 0.233 ms average (totalRuns=21426; inSeconds=5)
pub(crate) fn part2(result: &[String]) -> String {
    let mut abc = result
        .iter()
        .filter(|id| id.contains("-"))
        .map(|x| {
            let split: Vec<String> = x.split("-").map(|x| x.to_string()).collect();
            (split[0].clone(), split[1].clone())
        })
        .map(|(a, b)| {
            Range(
                a.parse::<i128>().expect("Expected a number for A"),
                b.parse::<i128>().expect("Expected a number for B"),
            )
        })
        .collect::<Vec<Range>>();

    abc.sort_by_key(|x| x.0);

    let ranges = mergeRanges(abc);

    let fresh_count = ranges
        .iter()
        .map(|range| range.1 - range.0 + 1)
        .sum::<i128>();

    fresh_count.to_string()
}

fn mergeRanges(mut input: Vec<Range>) -> Vec<Range> {
    input.sort_by_key(|x| x.0);
    let mut result: Vec<Range> = Vec::new();

    result.push(Range(input[0].0, input[0].1));

    input.iter().for_each(|current_range| {
        let last = result
            .last()
            .expect("No last found, there should have been value added already");

        if (current_range.0 > last.1 + 1) {
            result.push((Range(current_range.0, current_range.1)));
            return;
        } else {
            let last = result
                .pop()
                .expect("No last found, there should have been value added already");

            let new_range = Range(last.0, maxOf(last.1, current_range.1));

            result.push(new_range)
        }
    });

    result
}

fn maxOf(a: i128, b: i128) -> i128 {
    if (a > b) {
        return a;
    }
    b
}
