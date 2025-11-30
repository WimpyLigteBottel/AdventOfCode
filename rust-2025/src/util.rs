use std::fs::File;
use std::io::Read;

pub(crate) fn read_results(path: &str) -> Vec<String> {
    let mut buffer = String::new();
    let mut f = File::open(path).expect("Unable to open file");
    f.read_to_string(&mut buffer).expect("Unable to read file");

    buffer
        .lines() // splits on \n or \r\n automatically
        .map(|s| s.to_string()) // convert &str → String
        .collect()
}
