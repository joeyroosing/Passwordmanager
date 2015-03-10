/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.svarasoft.passwordmanager.datamanager;

import java.sql.Connection;

/**
 *
 * @author Joey
 */
public interface DatabaseManager {
    Connection connect();
}
