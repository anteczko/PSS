package com.example.demo.service;

import com.example.demo.model.Delegation;
import com.example.demo.repository.DelegationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class DelegationService {
    @Autowired
    private DelegationRepository delegationRepository;



    public Delegation addDelegation(int userId,Delegation delegation){
        return delegationRepository.save(delegation);
    }

    public boolean removeDelegation(int userId,int delegationId){
        if(delegationRepository.existsById(delegationId)){
            Delegation temp=delegationRepository.findById(delegationId).get();

            if( temp.getUser().getUserId()==userId ){
                delegationRepository.deleteById(userId);
                return true;
            }
        }
        return false;
    }

    public boolean changeDelegation(int delegationId,Delegation delegation){
        if(delegationRepository.existsById(delegationId)){
            Delegation temp=delegationRepository.findById(delegationId).get();
            delegationRepository.save(delegation);
            return true;
        }
        return false;
    }

    public List<Delegation> getAllDelegations(){return delegationRepository.findAll();}

    public List<Delegation> getAllDelegationsOrderByDateStartDesc(){return delegationRepository.findAll().stream().sorted(Comparator.comparing(Delegation::getDateTimeStart)).collect(Collectors.toList());}

    public List<Delegation> getAllDelegationsByUserOrderByDateStartDesc(int userId){
        return delegationRepository.findAll().stream().filter(s->s.getUser().getUserId()==userId).
                sorted(Comparator.comparing(Delegation::getDateTimeStart)).collect(Collectors.toList());
    }

}
