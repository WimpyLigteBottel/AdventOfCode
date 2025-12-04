use rayon::prelude::*;

/*
day3 part 1 took 0.029 ms average (totalRuns=169780; inSeconds=5)
day3 part 2 took 0.029 ms average (totalRuns=170644; inSeconds=5)
 */
pub(crate) fn part1(result: &[String]) -> String {
    result
        .par_iter()
        .map(|curr| find_highest_number(curr, 2))
        .sum::<i128>()
        .to_string()
}

pub(crate) fn part2(result: &[String]) -> String {
    result
        .par_iter()
        .map(|curr| find_highest_number(curr, 12))
        .sum::<i128>()
        .to_string()
}

fn find_highest_number(input: &String, target: i16) -> i128 {
    let mut stack: Vec<u32> = Vec::new();

    // how many characters we can remove
    let mut allowed_to_remove = input.len() as i16 - target;

    // process first digit
    stack.push(input[0..1].parse::<u32>().unwrap());

    for c in 1..input.len() {
        // safely read one digit
        let digit = input[c..c+1].parse::<u32>().unwrap();

        // IMPORTANT: recompute comparison inside the loop
        while allowed_to_remove > 0
            && !stack.is_empty()
            && stack.last().unwrap() < &digit
        {
            stack.pop();
            allowed_to_remove -= 1;
        }

        stack.push(digit);
    }

    // Trim to target length
    stack.truncate(target as usize);

    // Convert stack of u32 → string → i128
    let new_value: String = stack.iter().map(|d| d.to_string()).collect();

    new_value
        .parse::<i128>()
        .expect("The string to be integer in find_highest_number")
}

