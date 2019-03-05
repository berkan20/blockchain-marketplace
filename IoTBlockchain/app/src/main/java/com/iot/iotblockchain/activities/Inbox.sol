pragma solidity ^0.5.4;

contract Inbox {

    string public message;
    int public counter=0;

    constructor (string memory initialMessage) public {
        message = initialMessage;
    }

    function setMessage(string memory newMessage) public {
        message = newMessage;
    }
    function getMessage() public{
        counter++;
    }
}