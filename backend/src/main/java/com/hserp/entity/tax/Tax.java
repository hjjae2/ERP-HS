package com.hserp.entity.tax;

import com.hserp.entity.CommonTime;
import com.hserp.entity.person.Person;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Getter
@Entity
public class Tax extends CommonTime {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = true)
    private LocalDate issueDate;

    @Column(nullable = true)
    private LocalDate sendingDate;

    @Column(length = 255, nullable = true)
    private String remark;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "issue_status", nullable = false)
    private TaxIssueStatus issueStatus;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sending_status", nullable = false)
    private TaxSendingStatus sendingStatus;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "worker", nullable = false)
    private Person worker;
}
