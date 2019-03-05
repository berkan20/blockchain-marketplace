import React, { Component } from 'react';
import './App.css';

class App extends Component {
  constructor(props){
    super(props);
    //ABI goes here!!!!!!!!!
    const MyContract = window.web3.eth.contract([
	{
		"anonymous": false,
		"inputs": [
			{
				"indexed": true,
				"name": "_from",
				"type": "address"
			},
			{
				"indexed": true,
				"name": "_to",
				"type": "address"
			},
			{
				"indexed": false,
				"name": "dec_key",
				"type": "string"
			},
			{
				"indexed": false,
				"name": "sensor_type",
				"type": "uint256"
			},
			{
				"indexed": false,
				"name": "index",
				"type": "uint256"
			},
			{
				"indexed": false,
				"name": "swarm",
				"type": "string"
			}
		],
		"name": "payload_response",
		"type": "event"
	},
	{
		"constant": false,
		"inputs": [
			{
				"name": "device_address",
				"type": "address"
			}
		],
		"name": "add_valid_device",
		"outputs": [
			{
				"name": "",
				"type": "address"
			}
		],
		"payable": false,
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"constant": false,
		"inputs": [
			{
				"name": "_pub_key",
				"type": "string"
			}
		],
		"name": "customer_register",
		"outputs": [
			{
				"name": "",
				"type": "address"
			}
		],
		"payable": false,
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"constant": false,
		"inputs": [],
		"name": "deposit",
		"outputs": [
			{
				"name": "",
				"type": "bool"
			}
		],
		"payable": true,
		"stateMutability": "payable",
		"type": "function"
	},
	{
		"constant": false,
		"inputs": [
			{
				"name": "addr",
				"type": "address"
			}
		],
		"name": "get_vendor2",
		"outputs": [
			{
				"name": "prefix",
				"type": "address"
			}
		],
		"payable": false,
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"constant": false,
		"inputs": [],
		"name": "kill",
		"outputs": [],
		"payable": false,
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"constant": false,
		"inputs": [
			{
				"name": "vendor_address",
				"type": "address"
			},
			{
				"name": "sensor_type",
				"type": "uint256"
			},
			{
				"name": "index",
				"type": "uint256"
			}
		],
		"name": "request_for_data",
		"outputs": [
			{
				"name": "",
				"type": "address"
			}
		],
		"payable": false,
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"constant": false,
		"inputs": [
			{
				"name": "vendor_address",
				"type": "address"
			},
			{
				"name": "sensor_type",
				"type": "uint256"
			},
			{
				"name": "schema",
				"type": "string"
			},
			{
				"name": "timestamp",
				"type": "uint256"
			},
			{
				"name": "spatial",
				"type": "string"
			},
			{
				"name": "swarm",
				"type": "string"
			},
			{
				"name": "key_index",
				"type": "uint256"
			},
			{
				"name": "_encID",
				"type": "uint256"
			}
		],
		"name": "sensor_data_push",
		"outputs": [
			{
				"name": "",
				"type": "address"
			}
		],
		"payable": false,
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"anonymous": false,
		"inputs": [
			{
				"indexed": true,
				"name": "_from",
				"type": "address"
			},
			{
				"indexed": true,
				"name": "_to",
				"type": "address"
			},
			{
				"indexed": false,
				"name": "pub_key",
				"type": "string"
			},
			{
				"indexed": false,
				"name": "sensor_type",
				"type": "uint256"
			},
			{
				"indexed": false,
				"name": "index",
				"type": "uint256"
			}
		],
		"name": "payload_request",
		"type": "event"
	},
	{
		"constant": false,
		"inputs": [
			{
				"name": "dec_key",
				"type": "string"
			},
			{
				"name": "_to",
				"type": "address"
			},
			{
				"name": "sensor_type",
				"type": "uint256"
			},
			{
				"name": "index",
				"type": "uint256"
			}
		],
		"name": "transfer_key_and_data",
		"outputs": [
			{
				"name": "key",
				"type": "string"
			}
		],
		"payable": false,
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"constant": false,
		"inputs": [
			{
				"name": "sensor_type",
				"type": "uint256"
			},
			{
				"name": "price",
				"type": "uint256"
			}
		],
		"name": "update_sensor_price",
		"outputs": [
			{
				"name": "",
				"type": "uint256"
			}
		],
		"payable": false,
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"constant": false,
		"inputs": [
			{
				"name": "prefix",
				"type": "string"
			},
			{
				"name": "sensors",
				"type": "uint256[]"
			},
			{
				"name": "costs",
				"type": "uint256[]"
			}
		],
		"name": "vendor_register",
		"outputs": [
			{
				"name": "add",
				"type": "address"
			}
		],
		"payable": false,
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"constant": false,
		"inputs": [
			{
				"name": "vendor_address",
				"type": "address"
			},
			{
				"name": "vote",
				"type": "uint256"
			}
		],
		"name": "vote_for_vendor",
		"outputs": [
			{
				"name": "",
				"type": "uint256"
			}
		],
		"payable": false,
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"constant": false,
		"inputs": [],
		"name": "withdraw",
		"outputs": [],
		"payable": false,
		"stateMutability": "nonpayable",
		"type": "function"
	},
	{
		"payable": true,
		"stateMutability": "payable",
		"type": "fallback"
	},
	{
		"inputs": [],
		"payable": false,
		"stateMutability": "nonpayable",
		"type": "constructor"
	},
	{
		"constant": true,
		"inputs": [
			{
				"name": "sensor_type_index",
				"type": "uint256"
			}
		],
		"name": "get_sensor_price",
		"outputs": [
			{
				"name": "",
				"type": "uint256"
			}
		],
		"payable": false,
		"stateMutability": "view",
		"type": "function"
	},
	{
		"constant": true,
		"inputs": [
			{
				"name": "addr",
				"type": "address"
			}
		],
		"name": "get_vendor",
		"outputs": [
			{
				"name": "prefix",
				"type": "string"
			}
		],
		"payable": false,
		"stateMutability": "view",
		"type": "function"
	},
	{
		"constant": true,
		"inputs": [],
		"name": "getTest",
		"outputs": [
			{
				"name": "result",
				"type": "address"
			}
		],
		"payable": false,
		"stateMutability": "view",
		"type": "function"
	},
	{
		"constant": true,
		"inputs": [
			{
				"name": "sensor_type",
				"type": "uint256"
			},
			{
				"name": "index",
				"type": "uint256"
			}
		],
		"name": "query_sensor",
		"outputs": [
			{
				"name": "result",
				"type": "address"
			}
		],
		"payable": false,
		"stateMutability": "view",
		"type": "function"
	},
	{
		"constant": true,
		"inputs": [
			{
				"name": "vendor_address",
				"type": "address"
			},
			{
				"name": "sensor_type",
				"type": "uint256"
			}
		],
		"name": "sensor_data_length",
		"outputs": [
			{
				"name": "len",
				"type": "uint256"
			}
		],
		"payable": false,
		"stateMutability": "view",
		"type": "function"
	},
	{
		"constant": true,
		"inputs": [
			{
				"name": "vendor_address",
				"type": "address"
			},
			{
				"name": "sensor_type",
				"type": "uint256"
			},
			{
				"name": "index",
				"type": "uint256"
			}
		],
		"name": "sensor_data_pull",
		"outputs": [
			{
				"name": "schema",
				"type": "string"
			},
			{
				"name": "timestamp",
				"type": "uint256"
			},
			{
				"name": "spatial",
				"type": "string"
			},
			{
				"name": "price",
				"type": "uint256"
			}
		],
		"payable": false,
		"stateMutability": "view",
		"type": "function"
	},
	{
		"constant": true,
		"inputs": [],
		"name": "test",
		"outputs": [
			{
				"name": "",
				"type": "address"
			}
		],
		"payable": false,
		"stateMutability": "view",
		"type": "function"
	},
	{
		"constant": true,
		"inputs": [],
		"name": "vendor_length",
		"outputs": [
			{
				"name": "length",
				"type": "uint256"
			}
		],
		"payable": false,
		"stateMutability": "view",
		"type": "function"
	}
]
);
    this.state = {
      //contract address goes here!!!!!!!
      ContractInstance: MyContract.at('0x996ac95121ce79b119991d921dfa44b6553c95b6'),
      vendor_size: 0,
      vendor_address: 0,
      vendor_prefix: "zeki"
    }
    this.queryVendorLength = this.queryVendorLength.bind(this);
    this.queryVendorPrefixByAddress = this.queryVendorPrefixByAddress.bind(this);

  }//end of constructor


//methods begin
  queryVendorLength(){
    const{ vendor_length } = this.state.ContractInstance;

    vendor_length((err,length) => {
      if(err) console.error("Something Wrong!", err);
      this.setState({
        vendor_size: length.toNumber()
      })
    })
  }


  queryVendorPrefixByAddress(event){
    alert("Revealing!");
    event.preventDefault ();
    const{ get_vendor } = this.state.ContractInstance;
    const{ vendor_address: address } = this.state;
    console.log(address);
    get_vendor(
      address,
      {
        gas: 3000000,
        from: window.web3.eth.accounts[0]
      }, (err, result) => {
        if(err) console.error("Something Wrong!", err);
        this.setState({
          vendor_prefix: result
        })
      }
    )

    console.log(address);
    console.log(this.state.vendor_prefix);
    console.log(address)
  }


  render() {
    return (
      <div className="App">
        <header className="App-header">
          <h1 className="App-title"> Iot Marketplace</h1>
        </header>
        <br />
        <button onClick={ this.queryVendorLength }> Click to view Vendor size</button>
        <br />
        {this.state.vendor_size}
        <br />
        <br />




        <form onSubmit={ this.queryVendorPrefixByAddress }>
          <label>
            Enter Vendor Address:
            <input
              name="vendor_address"
              value={this.state.vendor_address}
              onChange={ event => this.setState ({ vendor_address: event.target.value }) } />
          </label>
          <button type="submit"> Submit </button>
        </form>

        {this.state.vendor_prefix}


      </div>
    );
  }

}

export default App;
