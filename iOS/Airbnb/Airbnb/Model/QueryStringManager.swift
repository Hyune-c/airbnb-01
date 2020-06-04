//
//  FilterManager.swift
//  Airbnb
//
//  Created by 신한섭 on 2020/06/03.
//  Copyright © 2020 신한섭. All rights reserved.
//

import Foundation

class QueryStringManager {
    
    var currentLocation: String?
    var page: Int?
    var dateFilter: DateFilter?
    var guestInfo: GuestInfo?
    
    func queryString() -> String {
        var query = ""
        if let location = currentLocation {
            query = EndPoint.location + location
        }
        
        if let page = page {
            query += "&" + EndPoint.PageCount + "\(page)"
        }
        
        if let dateFilter = dateFilter {
            query += "&" + EndPoint.checkIn + "\(dateFilter.startDate.convertToString)" + "&" + EndPoint.checkOut + "\(dateFilter.endDate.convertToString)"
        }
        
        if let guestInfo = guestInfo {
            query += "&" + EndPoint.guest + guestInfo.totalGuest()
        }
        
        return query
    }
}

struct DateFilter {
    var startDate: Date
    var endDate: Date
}

struct GuestInfo {
    var adult: String
    var youth: String
    var infants: String
    
    func totalGuest() -> String {
        guard let adultInteger = Int(adult), let youthInteger = Int(youth), let infantsInteger = Int(infants) else {return "0"}
        return String(adultInteger + youthInteger + infantsInteger)
    }
}
