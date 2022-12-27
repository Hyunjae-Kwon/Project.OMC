const main = document.querySelector("#main");
const qna = document.querySelector("#qna");
const result = document.querySelector("#result");

const endPoint = 6;
const select = [0, 0, 0, 0, 0, 0];

function calResult(){
  console.log(select);
  var result = select.indexOf(Math.max(...select));
  return result;
}

function setResult(){
  let point = calResult();
  const resultName = document.querySelector('.resultname');
  resultName.innerText = "<" + infoList[point].name + ">";
  
  let category = infoList[point].name;
  $('.resultname').val(category);
  
  resultSet();

  var resultImg = document.createElement('img');
  const imgDiv = document.querySelector('#resultImg');
  var imgURL = 'resources/img/foodtest/image-' + point + '.png';
  resultImg.src = imgURL;
  resultImg.alt = point;
  resultImg.classList.add('img-fluid');
  imgDiv.appendChild(resultImg);

  const resultDesc = document.querySelector('.resultDesc');
  resultDesc.innerHTML = infoList[point].desc;
}

function goResult(){
  qna.style.WebkitAnimation = "fadeOut 0.5s";
  qna.style.animation = "fadeOut 0.5s";
  setTimeout(() => {
    result.style.WebkitAnimation = "fadeIn 0.5s";
    result.style.animation = "fadeIn 0.5s";
    setTimeout(() => {
      qna.style.display = "none";
      result.style.display = "block"
    }, 200)})
    let point = calResult();
	  const resultName = document.querySelector('.resultname');
	  resultName.innerText = "<" + infoList[point].name + ">";
	  
	  let category = infoList[point].name;
	  $('.resultname').val(category);
	  
	  resultSet();
}

function resultSet(){
	var category = $(document).find('.resultname').val();
	location.href='foodTestResult.omc?GD_CATEGORY=' + category;
}

function addAnswer(answerText, qIdx, idx){
  var a = document.querySelector('.answerBox');
  var answer = document.createElement('button');
  answer.classList.add('answerList');
  answer.classList.add('my-3');
  answer.classList.add('py-3');
  answer.classList.add('mx-auto');
  answer.classList.add('fadeIn');
  
  a.appendChild(answer);
  answer.innerHTML = answerText;

  answer.addEventListener("click", function(){
    var children = document.querySelectorAll('.answerList');
    var img = document.querySelectorAll('.img-fluid');
    for(let i = 0; i < children.length; i++){
      children[i].disabled = true;
      children[i].style.WebkitAnimation = "fadeOut 0.5s";
      children[i].style.animation = "fadeOut 0.5s";
      img[i].disabled = true;
      img[i].style.WebkitAnimation = "fadeOut 0.5s";
      img[i].style.animation = "fadeOut 0.5s";
    }
    setTimeout(() => {
      var target = qnaList[qIdx].a[idx].type;
      for(let i = 0; i < target.length; i++){
        select[target[i]] += 1;
      }

      for(let i = 0; i < children.length; i++){
        children[i].style.display = 'none';
        img[i].style.display = 'none';
      }
      goNext(++qIdx);
    },200)
  }, false);
}

function goNext(qIdx){
  if(qIdx + 1 === endPoint){
    goResult();
    return;
  }

  var q = document.querySelector('.qBox');
  q.innerHTML = qnaList[qIdx].q;
  
  var questionImg = document.createElement('img');
  const imgDiv = document.querySelector('#qImg');
  var imgURL = 'resources/img/foodtest/question/image-' + qIdx + '.png';
  questionImg.src = imgURL;
  questionImg.alt = qIdx;
  questionImg.classList.add('img-fluid');
  imgDiv.appendChild(questionImg);
  
  for(let i in qnaList[qIdx].a){
    addAnswer(qnaList[qIdx].a[i].answer, qIdx, i);
  }
  var status = document.querySelector('.statusBar');
  status.style.width = (100/endPoint) * (qIdx+1) + '%';
}

function begin(){
  main.style.WebkitAnimation = "fadeOut 0.5s";
  main.style.animation = "fadeOut 0.5s";
  setTimeout(() => {
    qna.style.WebkitAnimation = "fadeIn 0.5s";
    qna.style.animation = "fadeIn 0.5s";
    setTimeout(() => {
      main.style.display = "none";
      qna.style.display = "block"
    }, 200)
    let qIdx = 0;
    goNext(qIdx);
  }, 200);
}
