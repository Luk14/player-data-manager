'use strict'

const URL = "http://localhost:8085/user";

// Showers
const READ_ALL = document.querySelector("#allusers");
const USER = document.querySelector("#user");
const CREATED_USER = document.querySelector("#newuser");
const DELETE_USER = document.querySelector("#deleteduser");
const UPDATE_USER = document.querySelector("#updateduser")

// Getters for Read
const GET_USER_ID_READ = document.querySelector("#userid");

//Getters for Create
const GET_USER_USERNAME_C = document.querySelector("#username");
const GET_USER_FNAME_C = document.querySelector("#fname");
const GET_USER_LNAME_C = document.querySelector("#lname");
const GET_USER_EMAIL_C = document.querySelector("#email");

//Getters for Delete
const GET_USER_DELETE_ID = document.querySelector("#userid_del");

//Getters for Update
const GET_USER_ID_U = document.querySelector("#id-update");
const GET_USER_USERNAME_U = document.querySelector("#username-update");
const GET_USER_FNAME_U = document.querySelector("#fname-update");
const GET_USER_LNAME_U = document.querySelector("#lname-update");
const GET_USER_EMAIL_U = document.querySelector("#email-update");

const readall = () => {
    fetch(`${URL}/readall`)
        .then((response) => {
            if (response.status !== 200) {
                console.error(`Something went wrong! Code: ${response.status}`);
            }
            else {
                response.json().then(json => {
                    removeReq("allusers");
                    for (let i = 0; i < json.length; i++) {
                        let p = document.createElement("p");
                        let info = document.createTextNode(`#${i + 1} | ${json[i].id} | ${json[i].username} | ${json[i].fname} | ${json[i].email}`);
                        p.appendChild(info);
                        for (let x = 0; x < json[i].cars.length; x++) {
                            let a = document.createElement("a");
                            let infocar = document.createTextNode(` | Car IDs | ${json[i].cars[x].id},`);
                            a.appendChild(infocar);
                            p.appendChild(a);
                        }
                        READ_ALL.appendChild(p);
                    }
                })
            }
        }).catch(err => console.error(`Stop! Info: ${err}`));
}

const read = () => {
    fetch(`${URL}/read/${GET_USER_ID_READ.value}`, {
        method: "GET", headers: {
            "Content-Type": "application/json",
        }
    }).then(response => response.json())
        .then(json => {
            removeReq("user");
            let p = document.createElement("p");
            let info = document.createTextNode(`#${json.id} | ${json.username} | ${json.fname} | ${json.email}`);
            p.appendChild(info);
            USER.appendChild(p);
        }).catch(err => console.error(`Stop! Info: ${err}`));
}

const create = () => {
    fetch(`${URL}/create`, {
        method: 'POST',
        body: JSON.stringify({
            "username": GET_USER_USERNAME_C.value,
            "fname": GET_USER_FNAME_C.value,
            "lname": GET_USER_LNAME_C.value,
            "email": GET_USER_EMAIL_C.value
        }),
        headers: {
            'Content-type': 'application/json'
        },
    }).then((response) => response.json())
        .then((json) => {
            removeReq("newuser");
            let p = document.createElement("p");
            let info = document.createTextNode(`#${json.id} | ${json.username} | ${json.fname} | ${json.email}`);
            p.appendChild(info);
            CREATED_USER.appendChild(p);
        }).catch(err => console.error(`Stop! Info: ${err}`));
}

const deleteu = () => {
    fetch(`${URL}/delete/${GET_USER_DELETE_ID.value}`, {
        method: "DELETE",
    })
        .then(() => {
            removeReq("deleteduser");
            let p = document.createElement("p");
            let info = document.createTextNode(`User has been Removed`);
            p.appendChild(info);
            DELETE_USER.appendChild(p);
        }).catch(err => console.error(`Stop! Info: ${err}`));
}

const update = () => {
    fetch(`${URL}/update/${GET_USER_ID_U.value}`, {
        method: 'PUT',
        body: JSON.stringify({
            "id": GET_USER_ID_U.value,
            "username": GET_USER_USERNAME_U.value,
            "fname": GET_USER_FNAME_U.value,
            "lname": GET_USER_LNAME_U.value,
            "email": GET_USER_EMAIL_U.value
        }),
        headers: {
            'Content-type': 'application/json'
        },
    }).then((response) => response.json())
        .then((json) => {
            removeReq("updateduser");
            let p = document.createElement("p");
            let info = document.createTextNode(`#${json.id} | ${json.username} | ${json.fname} | ${json.email}`);
            p.appendChild(info);
            UPDATE_USER.appendChild(p);
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