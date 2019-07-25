let pushCount = 3;

window.onload = function() {
    let date = new Date(); // Or the date you'd like converted.
    let isoDate = new Date(date.getTime() - (date.getTimezoneOffset() * 60000)).toISOString();
    let now = isoDate.substr(0, 16);

    let tempBuildAt = document.getElementById("temp_buildAt");
    let buildAtDom = document.getElementById("buildAt");
    let addCountAtDom = document.getElementById("addCount");
    let minusCountAtDom = document.getElementById("minusCount");
    let submitDom = document.getElementById("submit_btn");
    let formDom = document.getElementById("form");

    submitDom.addEventListener("click", function (e) {
        e.preventDefault();
        updatePushCount();
        formDom.submit();
    })

    addCountAtDom.addEventListener("click", function (e) {
        addCount(e);
    });

    minusCountAtDom.addEventListener("click", function (e) {
        minusCount(e);
    });

    tempBuildAt.addEventListener("focusout", updateBuildAt)
    tempBuildAt.value = now;
    buildAtDom.value = now + ':00z';


}

function updateBuildAt() {
    let tempBuildAtDom = document.getElementById("temp_buildAt");
    let buildAtDom = document.getElementById("buildAt");
    let tempBuildAt = tempBuildAtDom.value;
    buildAtDom.value = tempBuildAt + ':00Z';
}

function addCount(event) {
    event.preventDefault();

    pushCount += 1;
    updatePushCount();
}

function minusCount(event) {
    event.preventDefault();

    pushCount -= 1;
    updatePushCount();
}

function updatePushCount() {
    document.getElementById("temp_pushCount").innerHTML = pushCount;
    document.getElementById("pushCount").value = pushCount;
}

function exhaustGas() {
    let answer = confirm("Are you sure?");
    if (answer) {
        location.href = document.getElementById("exhaustPath").value
    }
}