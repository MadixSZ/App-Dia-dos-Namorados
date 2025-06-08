package com.example.amorzinho.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.amorzinho.R
import com.example.amorzinho.adapters.TimelineAdapter
import com.example.amorzinho.models.Memory

class TimelineFragment : Fragment(R.layout.activity_fragment_timeline) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val timelineEvents: List<Memory> = listOf(
            Memory(
                "Início",
                "2021",
                "Tudo começou no dia em que aceitei jogar Fortnite com um menino aleatório."
            ),
            Memory(
                "Ligações",
                "2021",
                "Passamos a entrar em call todos os dias. Ficávamos até altas horas, sempre com um assunto diferente. "
                        + "Lembro que eu morria de vergonha de ligações, mas com você parecia que tudo se encaixava com facilidade."
            ),
            Memory(
                "Primeiro Encontro",
                "25/04/2021",
                "Lembro que aceitei ir com minha amiga pra Tambaú só pra te ver. "
                        + "Eu tava super nervosa, e meu WhatsApp ainda não ajudou te, "
                        + "enviando a localização de outro país kkkkk. "
                        + "Mas assim que começamos a conversar, o nervosismo passou."
            ),
            Memory(
                "Sua primeira vez em casa",
                "15/05/2021",
                "Lembro que você fez aquela listinha boba por eu ter te dado um spoiler. " +
                        "Onde nela, até o final do dia eu deveria: te dar abraços, beijos, " +
                        "assistir anime boiolinha com você de conchinha, te dar um " +
                        "soco, usar a peruca rosa e te apresentar para a minha mãe como o " +
                        "genro dela (já sabemos como isso acabou)."
            ),
            Memory(
                "Primeiro Beijo",
                "23/05/2021",
                "A gente pediu pizza, e você acabou perdendo o ônibus e chamou seu tio pra te " +
                        "buscar.No caminho até a praça, paramos em um cantinho escuro perto de" +
                        " um comércio fechado. Os dois nervosos e atrapalhados e no " +
                        "meio da bagunça daquele dia, rolou nosso primeiro beijo. "
            ),
            Memory(
                "A Aliança Guardada",
                "2021/2022",
                "Lembro quando você começou a perguntar a medida do meu dedo, jurando que " +
                        "eu não ia desconfiar de nada kkkkkkk. Ainda assim, comprou a aliança e " +
                        "manteve guardada por meses, até passar uma fase difícil que eu estava " +
                        "enfrentando. Eu achei isso uma atitude incrível, mesmo sem saber se a " +
                        "aliança ia me servir, você a guardou, esperando eu estar bem para " +
                        "começar nosso relacionamento e usá-la."
            ),
            Memory(
                "Início do nosso namoro",
                "30/01/2022",
                "Lembro até hoje que você começou com esse assunto na brincadeira, e eu aceitei. " +
                        "Foi interessante ver sua reação de assustado/surpreso, mas foi uma das " +
                        "melhores decisões que tomei na vida."
            ),
            Memory(
                "Nosso Primeiro Piquenique",
                "22/04/2023",
                "Foi uma das experiências mais especiais que já tive com você. Preparamos as comidinhas, " +
                        "fizemos brigadeiro, compramos morango, uva, suco, pão... Sentamos ali no " +
                        "mato, sendo atacados pelas formigas kkkk, mas nada disso importava, " +
                        "porque eu estava ao seu lado. Ficamos até anoitecer, comendo, rindo e conversando. " +
                        "O tempo passou tão rápido... Se eu pudesse, teria congelado aquele momento pra sempre."
            ),
            Memory(
                "Quando me mudei",
                "03/03/2024",
                "Me mudar, embora eu não tenha demonstrado muito, foi uma decisão difícil. Eu não" +
                        "sabia se, ao fazer isso, estaria decretando o fim do nosso relacionamento " +
                        "ou não. Relacionamento à distância é algo que desgasta, que exige muito. " +
                        "Mas você não só me apoiou, como também ficou ao meu lado, lutando para " +
                        "fazermos dar certo. Isso foi muito importante pra mim — e pra nós."
            )
        )

        val recyclerView: RecyclerView = view.findViewById(R.id.timelineRecycler)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = TimelineAdapter(timelineEvents)
    }
}