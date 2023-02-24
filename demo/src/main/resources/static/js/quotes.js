const quotes = [
{
    quote :"당신이 인생의 주인공이기 때문이다. 그사실을 잊지마라. 지금까지 당신이 만들어온 의식적 그리고 무의식적 선택으로 인해 지금의 당신이 있는것이다.",
    author:"바바라 홀",
}, 
{
    quote :"세상은 고통으로 가득하지만 그것을 극복하는 사람들로도 가득하다.",
    author:"헨렌켈러",
},
{
    quote :"물러나서 조용하게 구하면 배울 수 있는 스승은 많다. 사람은 가는 곳마다 보는 것마다 모두 스승으로서 배울 것이 많은 법이다.",
    author:"맹자",
},
{
    quote :"너무 소심하고 까다롭게 자신의 행동을 고민하지 말라 . 모든 인생은 실험이다 . 더많이 실험할수록 더나아진다.",
    author:"랄프 왈도 에머슨",
},
{
    quote :"오랫동안 꿈을 그리는 사람은 마침내 그 꿈을 닮아 간다.",
    author:"앙드레 말로",
},
{
    quote :"네 믿음은 네 생각이 된다 . 네 생각은 네 말이 된다. 네말은 네 행동이 된다 네행동은 네 습관이된다 . 네 습관은 네 가치가 된다 . 네 가치는 네 운명이 된다.",
    author:"간디",
},
{
    quote :"나이가 60이다 70이다 하는 것으로 그 사람이 늙었다 젊었다 할 수 없다. 늙고 젊은 것은 그 사람의 신념이 늙었느냐 젊었느냐 하는데 있다.",
    author:"맥아더",
},
{
    quote :"당신이 할수 있다고 믿든 할수 없다고 믿든 믿는 대로 될것이다.",
    author:"헨리 포드",
},
{
    quote :"우선 무엇이 되고자 하는가를 자신에게 말하라 그리고 해야 할일을 하라.",
    author:"에픽토테스",
},
{
    quote :"삶을 사는 데는 단 두가지 방법이 있다. 하나는 기적이 전혀 없다고 여기는 것이고 또 다른 하나는 모든 것이 기적이라고 여기는방식이다.",
    author:"알베르트 아인슈타인",
},
];

const quote = document.querySelector("#quote span:first-child");
const author = document.querySelector("#quote span:last-child");

const todaysQuote = quotes[Math.floor(Math.random() * quotes.length)];

quote.innerText = todaysQuote.quote;
author.innerText = todaysQuote.author;