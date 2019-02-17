pragma solidity ^0.5.4;

contract vendor {
    // contract owner
    address private creator;

    // IoT device data
    struct device_data {
        // link for detecting devices
        uint index;
        // blockchain timestamps stored swarm hashes  
        uint[] timestamps;
        // map timestamp values to swarm file hashes
        mapping(uint => string) filehashes;
    }
    // map device id's to their data (one data per id)
    mapping(address => device_data) private device_logs;
    // keep a separate device id array of all received id's
    address[] private device_index;
    // event to log action
    event log_action (address indexed device_id, uint index, uint timestamp, string filehash);

    // check if device is seen before?
    // https://medium.com/@robhitchens/solidity-crud-part-1-824ffa69509a
    function is_device_present (address device_id) public view returns (bool result) {
        // return false if no device present yet!
        if(device_index.length == 0) return false;
        // return true if device exists
        return (device_index[device_logs[device_id].index] == device_id);
    }

    // helper function for string concatenation
    // http://cryptodir.blogspot.com.tr/2016/03/solidity-concat-string.html
    function filehash_concat (string memory h1, string memory h2) internal pure returns (string memory concat) {
        bytes memory b1 = bytes(h1);
        bytes memory b2 = bytes(h2);
        string memory sm = new string(b1.length + 1 + b2.length);
        bytes memory bm = bytes(sm);
        uint i = 0;
        uint k = 0;
        for (i = 0; i < b1.length; i++) bm[k++] = b1[i];
        bm[k++] = ',';
        for (i = 0; i < b2.length; i++) bm[k++] = b2[i];
        return string(sm);
    }

    // push specific device data handle into the chain
    function set_device_data (address device_id, string memory filehash) public returns (uint index, uint timestamp) {
        uint ts;
        ts = block.timestamp;
        device_data storage current_device =  device_logs[device_id];
        if(is_device_present(device_id)) {
            // device already exists
            uint index_of_last_timestamp = current_device.timestamps.length-1;
            
            if( index_of_last_timestamp+1 == 0 ||
                current_device.timestamps[index_of_last_timestamp] < ts) {
                // first insertion or insertion made (at least) in last block 
                current_device.timestamps.push(ts);
                current_device.filehashes[ts] = filehash;
            } else if(current_device.timestamps[index_of_last_timestamp] == ts) {
                // data inserted before, lets concat data for that certain timestamp
                current_device.filehashes[ts] = filehash_concat(current_device.filehashes[ts], filehash);
            } else {
                // doc, this is heavy!
                assert(false);
            }
            // trigger event
            emit log_action(device_id, current_device.index, ts, filehash);
            return(current_device.index, ts);
        } else {
            // device received first time
            current_device.timestamps.push(ts);
            current_device.filehashes[ts] = filehash;
            current_device.index = device_index.push(device_id)-1;
            // trigger event
            emit log_action(device_id, device_index.length-1, ts, filehash);
            return(device_index.length-1, ts);
        }
    }

    // get received data at a certain timestamp for a specific device
    function get_device_data (address device_id, uint timestamp) public view returns (string memory hash) {
        if(!is_device_present(device_id)) revert();
        return device_logs[device_id].filehashes[timestamp];
    }

    // get all data timestamps for a specific device
    // TODO create a timestamp getter() with start-end interval
    function get_device_timestamps (address device_id) public view returns (uint[] memory timestamp) {
        if(!is_device_present(device_id)) revert();
        return device_logs[device_id].timestamps;
    }

    // get total device count
    function get_device_count() public view returns (uint count) {
        return device_index.length;
    }

    // get device address from index
    function get_device_at_index (uint index) public view returns (address device_address) {
        return device_index[index];
    }

    // constructor
    constructor() public {
        creator = msg.sender;
    }

    // kills contract and sends remaining funds back to creator
    function  kill() public  {
        if (msg.sender == creator) {
            selfdestruct(msg.sender);
        }
    }
}
