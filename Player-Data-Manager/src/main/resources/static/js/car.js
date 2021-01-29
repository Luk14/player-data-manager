'use strict'

const URL = "http://localhost:8085/car";

// Showers
const READ_ALL = document.querySelector("#allcars");
const CAR = document.querySelector("#car");
const CREATED_CAR = document.querySelector("#newcar");
const DELETE_CAR = document.querySelector("#deletedcar");
const UPDATE_CAR = document.querySelector("#updatedcar")

// Getters for Read
const GET_CAR_ID_READ = document.querySelector("#carid");

//Getters for Create
const GET_CAR_REG = document.querySelector("#reg");
const GET_CAR_PDATE = document.querySelector("#pdate");
const GET_CAR_COLOR = document.querySelector("#color");
const GET_CAR_TOPSPEED = document.querySelector("#topspeed");
const GET_CAR_ZTS = document.querySelector("#zts");
const GET_CAR_USERID = document.querySelector("#userid");

//Getters for Delete
const GET_CAR_DELETE_ID = document.querySelector("#carid_del");

//Getters for Update
const GET_CAR_REG_UPDATE = document.querySelector("#reg-update");
const GET_CAR_ID_UPDATE = document.querySelector("#carid-update");
const GET_CAR_PDATE_UPDATE = document.querySelector("#pdate-update");
const GET_CAR_COLOR_UPDATE = document.querySelector("#color-update");
const GET_CAR_TOPSPEED_UPDATE = document.querySelector("#topspeed-update");
const GET_CAR_ZTS_UPDATE = document.querySelector("#zts-update");
const GET_CAR_USERID_UPDATE = document.querySelector("#userid-update");

const readall = () => {
    fetch(`${URL}/readall`)
        .then((response) => {
            if (response.status !== 200) {
                console.error(`Something went wrong! Code: ${response.status}`);
            }
            else {
                response.json().then(json => {
                    removeReq("allcars");
                    for (let i = 0; i < json.length; i++) {
                        let p = document.createElement("p");
                        let info = document.createTextNode(`#${i + 1} | ${json[i].id} | ${json[i].registration} | ${json[i].productionDate} | ${json[i].color} | ${json[i].topSpeed} | ${json[i].zeroToSixty}`);
                        p.appendChild(info);
                        READ_ALL.appendChild(p);
                    }
                })
            }
        }).catch(err => console.error(`Stop! Info: ${err}`));
}

const read = () => {
    fetch(`${URL}/read/${GET_CAR_ID_READ.value}`, {
        method: "GET", headers: {
            "Content-Type": "application/json",
        }
    }).then(response => response.json())
        .then(json => {
            removeReq("car");
            let p = document.createElement("p");
            let info = document.createTextNode(`#${json.id} | ${json.registration} | ${json.productionDate} | ${json.color} | ${json.topSpeed} | ${json.zeroToSixty}`);
            p.appendChild(info);
            CAR.appendChild(p);
        }).catch(err => console.error(`Stop! Info: ${err}`));
}

const create = () => {
    fetch(`${URL}/create`, {
        method: 'POST',
        body: JSON.stringify({
            "registration": GET_CAR_REG.value,
            "productionDate": GET_CAR_PDATE.value,
            "color": GET_CAR_COLOR.value,
            "topSpeed": GET_CAR_TOPSPEED.value,
            "zeroToSixty": GET_CAR_ZTS.value,
            "user": {
                "id": GET_CAR_USERID.value
            }
        }),
        headers: {
            'Content-type': 'application/json'
        },
    }).then((response) => response.json())
        .then((json) => {
            removeReq("newcar");
            let p = document.createElement("p");
            let info = document.createTextNode(`#${json.id} | ${json.registration} | ${json.productionDate} | ${json.color} | ${json.topSpeed} | ${json.zeroToSixty}`);
            p.appendChild(info);
            CREATED_CAR.appendChild(p);
        }).catch(err => console.error(`Stop! Info: ${err}`));
}

const deleteu = () => {
    fetch(`${URL}/delete/${GET_CAR_DELETE_ID.value}`, {
        method: "DELETE",
    })
        .then(() => {
            removeReq("deletedcar");
            let p = document.createElement("p");
            let info = document.createTextNode(`Car has been Removed`);
            p.appendChild(info);
            DELETE_CAR.appendChild(p);
        }).catch(err => console.error(`Stop! Info: ${err}`));
}

const update = () => {
    fetch(`${URL}/update/${GET_CAR_ID_UPDATE.value}`, {
        method: 'PUT',
        body: JSON.stringify({
            "id": GET_CAR_ID_UPDATE.value,
            "registration": GET_CAR_REG_UPDATE.value,
            "productionDate": GET_CAR_PDATE_UPDATE.value,
            "color": GET_CAR_COLOR_UPDATE.value,
            "topSpeed": GET_CAR_TOPSPEED_UPDATE.value,
            "zeroToSixty": GET_CAR_ZTS_UPDATE.value,
            "user": {
                "id": GET_CAR_USERID_UPDATE.value
            }
        }),
        headers: {
            'Content-type': 'application/json'
        },
    }).then((response) => response.json())
        .then((json) => {
            removeReq("updatedcar");
            let p = document.createElement("p");
            let info = document.createTextNode(`#${json.id} | ${json.registration} | ${json.productionDate} | ${json.color} | ${json.topSpeed} | ${json.zeroToSixty}`);
            p.appendChild(info);
            UPDATE_CAR.appendChild(p);
        }).catch(err => console.error(`Stop! Info: ${err}`));
}

function removeReq(id) {
    document.getElementById(id).innerHTML = "";
}

document.getElementById("readall").addEventListener("click", readall);
document.getElementById("read").addEventListener("click", read);
document.getElementById("create").addEventListener("click", create);
document.getElementById("deleteu").addEventListener("click", deleteu);
document.getElementById("update").addEventListener("click", update);