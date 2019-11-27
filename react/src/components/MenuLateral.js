import React, { Component } from 'react';
import '../css/font-awesome.css';
import '../css/menu-lateral.css';


export default class MenuLateral extends Component{


    render(){
        return(        
            <nav class="main-menu">
                <div>
                    <a class="logo" href="http://startific.com">
                    </a> 
                </div> 
                <div class="settings"></div>
                <div class="scrollbar" id="style-1">
                    
                    <ul>
                        <li>                                   
                            <a href="http://startific.com">
                                <i class="fa fa-home fa-lg"></i>
                                <span class="nav-text">Home</span>
                            </a>
                        </li>   

                        <li>                                 
                            <a href="http://startific.com">
                                <i class="fa fa-user fa-lg"></i>
                                <span class="nav-text">Login</span>
                            </a>
                        </li>
                    </ul>
                </div>
            </nav>
        );
    }
}