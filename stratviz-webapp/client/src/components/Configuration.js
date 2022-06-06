import React, { Component }  from 'react';
import Button from 'react-bootstrap/Button'
import Dropdown from "react-bootstrap/Dropdown";
import DropdownButton from "react-bootstrap/DropdownButton";
const Configuration = () => {
    return (
        <div>
  <Dropdown id = "dropdown-menu" allign="end"  >
    <Dropdown.Toggle id="dropdown-button-dark-example1" variant="secondary">
      Configuration
    </Dropdown.Toggle>

    <Dropdown.Menu variant="dark">
      {/* <Dropdown.Item href="#/action-1" active>
      Existing configuration 1
      </Dropdown.Item> */}
      <Dropdown.Item href="#/action-1">Existing configuration 1</Dropdown.Item>
      <Dropdown.Item href="#/action-2">Existing configuration 2</Dropdown.Item>
      <Dropdown.Item href="#/action-3">Existing configuration 3</Dropdown.Item>
      <Dropdown.Divider />
      <Dropdown.Item href="#/action-4">Add a configuration</Dropdown.Item>
      <Dropdown.Item href="#/action-5">Edit a configuration</Dropdown.Item>
      <Dropdown.Item href="#/action-6">Delete a configuration</Dropdown.Item>
    </Dropdown.Menu>
  </Dropdown>


        </div>
    )
}

export default Configuration