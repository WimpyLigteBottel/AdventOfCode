mod util;

use std::collections::HashMap;
use std::ops::Mul;
use util::read_results;

fn main() {
    let path = "src/day1/input.txt";
    let result = read_results(path);

    println!("{}", day1(&result));
    println!("{}", day2(&result));
}

fn day2(result: &Vec<String>) -> i64 {
    let (smallest_left, smallest_right) = left_and_right(result,false);
    let mut map: HashMap<i64, i64> = HashMap::new();

    smallest_left.iter().for_each(|x| {
        let counter = smallest_right.iter().filter(|y| return y.eq(&x)).count() as i64;

        map.insert(i64::from(*x), i64::from(*x).mul(counter));
    });

    let mut total: i64 = 0;
    smallest_left.iter().for_each(|x| {
        let transformed = x.cast_unsigned() as i64;
        let value = map.get(&transformed).unwrap_or(&0);
        total += value;
    });

    total
}

fn day1(result: &Vec<String>) -> String {
    let (smallest_left, smallest_right) = left_and_right(result, true);
    let mut total = 0;

    if smallest_right.len() != smallest_left.len() {
        panic!("Something went wrong")
    }

    for i in 0..smallest_left.len() {
        let distance = smallest_left[i] - smallest_right[i];

        total += distance.abs();
    }

    total.to_string()
}

/*
Gets the smallest from left to right
*/
fn left_and_right(result: &Vec<String>, sort: bool) -> (Vec<i32>, Vec<i32>) {
    let mut smallest_left: Vec<i32> = Vec::new();
    let mut smallest_right: Vec<i32> = Vec::new();

    result.iter().for_each(|x| {
        let split: Vec<String> = x.split("   ").map(|x| x.to_string()).collect();
        smallest_left.push(split[0].parse().unwrap_or(0));
        smallest_right.push(split[1].parse().unwrap_or(0));
    });

    if sort {
        smallest_left.sort_unstable();
        smallest_right.sort_unstable();
    }

    (smallest_left, smallest_right)
}