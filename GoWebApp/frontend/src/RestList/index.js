import React, { Component } from 'react'
import RestCard from '../RestCard/RestCard'
import { Alert } from 'antd'
export default class index extends Component {
  /*{
    "Restaurant ID" : 1111111,
    "Restaurant Name" : "Sushi Tei",
    "Cuisines" : "Japanese, Sushi",
    "Average Cost for two" : 1500,
    "Currency" : "SGD",
    "Has Table booking" : "No",
    "Has Online delivery" : "No",
    "Aggregate rating" : 4.5,
    "Rating color" : "Dark Green",
    "Rating text" : "Excellent",
    "Votes" : 417
  }*/

  render() {
    const data__ = this.props.data;

    let y =  data__.map((d,i) => {
      return (
         <div  key={i} ><RestCard datanew={d} />  <br></br> </div>
        
      )
    });

    const r  =  (y.length > 0 ) ? (  y ) : (    <Alert type="error"  message="No more Reseults found !!" banner /> )
    
    return (
      <div id="RestResult">
      { r } 
      </div>
    )
  }
}
