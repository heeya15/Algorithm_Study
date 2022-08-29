function solution(record) {
  var answer = [];
  let dic = new Map();

  // id 값으로 해당 id의 유저의 마지막 닉네임을 얻어낸다
  for (let i = 0; i < record.length; i++) {
    let wordInfoArray = record[i].split(" ");
    if (wordInfoArray[0] === "Enter" || wordInfoArray[0] === "Change") {
      dic.set(wordInfoArray[1], wordInfoArray[2]);
    }
  }

  for (let i = 0; i < record.length; i++) {
    let info = record[i].split(" ");
    if (info[0] == "Enter") {
      answer.push(dic.get(info[1]) + "님이 들어왔습니다.");
    } else if (info[0] == "Leave") {
      answer.push(dic.get(info[1]) + "님이 나갔습니다.");
    }
  }
  // 배열을 순회하면서 새로운 배열을 생성시켜준다.
  //     let result = record.map((v) => {
  //         let info = v.split(' ')
  //         if (info[0] == "Enter") {
  //             return dic.get(info[1]) + "님이 들어왔습니다."
  //         } else if (info[0] == "Leave"){
  //             return dic.get(info[1]) + "님이 나갔습니다."
  //         } else {

  //         }
  //     })

  // console.log(answer)

  return answer;
}
