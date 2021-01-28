'use strict'

const URL = "http://localhost:8085/user";

const READ_ALL = document.querySelector("#allusers");
const USER = document.querySelector("#user");

const GET_USER_ID_READ = document.querySelector("#userid");

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
                        let info = document.createTextNode(`#${i} | ${json[i].id} | ${json[i].username} | ${json[i].fname} | ${json[i].email}`);
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

function removeReq(id) {
    document.getElementById(id).innerHTML = "";
}

document.getElementById("readall").addEventListener("click", readall);
document.getElementById("read").addEventListener("click", read);