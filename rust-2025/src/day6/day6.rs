struct OperatorAndIndex {
    operator: char,
    index: usize,
}

// part 1 took 0.087 ms average (totalRuns=57218; inSeconds=5)
pub(crate) fn part1(result: &[String]) -> String {
    let mut row1: Vec<String> = Vec::new();
    let mut row2: Vec<String> = Vec::new();
    let mut row3: Vec<String> = Vec::new();
    let mut row4: Vec<String> = Vec::new();

    let mut operator = get_operators_and_starting_index(&result);


    for x in 0..operator.len() - 1 {
        let current = operator[x].index;
        let next = operator[x + 1].index - 1;

        row1.push(result[0][current..next].to_string());
        row2.push(result[1][current..next].to_string());
        row3.push(result[2][current..next].to_string());
        row4.push(result[3][current..next].to_string());
    }

    // removed the extra operator to make my life easier
    operator.pop();

    let mut total: i128 = 0;

    for x in 0..operator.len() {
        let new_list = [&row1[x], &row2[x], &row3[x], &row4[x]].iter().map(|x| {
            let clean = x.replace(" ", "");

            clean.parse::<i128>().unwrap()
        }).collect();

        total += calculate_answer(operator[x].operator, new_list)
    }


    total.to_string()
}

fn calculate_answer(operator: char, new_list: Vec<i128>) -> i128 {
    let mut answer = 0;
    if (operator == '*') {
        answer = new_list.iter().fold(1, |acc, i| acc * i)
    }

    if (operator == '+') {
        answer = new_list.iter().fold(0, |acc, i| acc + i)
    }
    answer
}

fn get_operators_and_starting_index(result: &[String]) -> Vec<OperatorAndIndex> {
    let mut operators: Vec<OperatorAndIndex> = Vec::new();

    for index in 0..result[0].len() {
        //select teh operator
        let operator: String = result[4][index..index + 1].to_string();

        if operator != " " {
            operators.push(OperatorAndIndex {
                operator: operator.chars().nth(0).expect("There should be operator"),
                index,
            })
        }
    }
    return operators;
}

//part 2 took 0.034 ms average (totalRuns=146536; inSeconds=5)
pub(crate) fn part2(result: &[String]) -> String {
    let mut row1: Vec<String> = Vec::new();
    let mut row2: Vec<String> = Vec::new();
    let mut row3: Vec<String> = Vec::new();
    let mut row4: Vec<String> = Vec::new();

    let mut operator = get_operators_and_starting_index(&result);


    for x in 0..operator.len() - 1 {
        let current = operator[x].index;
        let next = operator[x + 1].index - 1;

        row1.push(result[0][current..next].to_string());
        row2.push(result[1][current..next].to_string());
        row3.push(result[2][current..next].to_string());
        row4.push(result[3][current..next].to_string());
    }

    // removed the extra operator to make my life easier
    operator.pop();

    let mut total: i128 = 0;

    for x in 0..operator.len() {
        let new_list: Vec<i128> = (0..row1[x].len())
            .map(|y| {
                let one = &row1[x][y..y+1];
                let two = &row2[x][y..y+1];
                let three = &row3[x][y..y+1];
                let four = &row4[x][y..y+1];

                let new = [one, two, three, four].join("").replace(" ", "").to_string();
                new.parse::<i128>().unwrap()
            })
            .collect();

        total += calculate_answer(operator[x].operator, new_list)
    }


    total.to_string()
}

