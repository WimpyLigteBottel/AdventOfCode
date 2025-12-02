use rayon::prelude::*;

pub(crate) fn part1(result: &Vec<String>) -> String {
    let digits = split_into_numbers(&*result);

    digits
        .par_iter()
        .filter_map(|d| {
            let (first,second) =  d.split_at(d.len() / 2);

            if first == second && !second.starts_with("0"){
                return d.parse::<i128>().ok()
            }
            return None;
        })
        .sum::<i128>()
        .to_string()
}

pub(crate) fn part2(result: &Vec<String>) -> String {
    let digits = split_into_numbers(&*result);

    digits
        .par_iter()
        .filter_map(|d| {
            let max = d.len() / 2 + 1;

            for i in 0..max {
                let temp = d[0..i].to_string();
                if d.replace(temp.as_str(), "").is_empty() {
                    return d.parse::<i128>().ok();
                }
            }
            return None;
        })
        .sum::<i128>()
        .to_string()
}

fn split_into_numbers(result: &[String]) -> Vec<String> {
    let first = result.first().expect("Should be single string only");

    first
        .split(',')
        .par_bridge()
        .flat_map(|x| {
            let parts: Vec<&str> = x.split('-').collect();

            let left: i64 = parts[0].parse().unwrap();
            let right: i64 = parts[1].parse().unwrap();

            // Pre-allocate with exact capacity
            let count = (right - left) as usize;
            let mut digits = Vec::with_capacity(count);

            for num in left..right {
                digits.push(num.to_string());
            }
            digits
        })
        .collect()
}
